package com.nbnfsoft.admin.manager;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nbnfsoft.admin.domain.dto.MeunDto;
import com.nbnfsoft.admin.domain.input.MeunListInput;
import com.nbnfsoft.admin.entity.NfsurMenu;
import com.nbnfsoft.admin.repository.NfsurMenuRepository;
import com.nbnfsoft.admin.utils.SecurityUtils;
import com.nbnfsoft.admin.utils.sail.Queryable;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 目录表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-05-06
 */
@Service
public class NfsurMenuManager {

    @Autowired
    NfsurMenuRepository nfsurMenuRepository;

    public long createOrUpdate(MeunDto dto) {

        if (dto.meun_id <= 0) {
            NfsurMenu entity = new NfsurMenu();

            entity.name = dto.name;
            entity.parent_id = dto.parent_id;
            entity.status = dto.status;
            entity.org_id=10001l;

            entity.create_id = SecurityUtils.getUserId();
            entity.create_date = new Date();

            int re = nfsurMenuRepository.insert(entity);

            if (re <= 0) throw new RuntimeException("insert fail");

            return entity.id;
        } else {
            int re = nfsurMenuRepository.getAll()
                    .eq(NfsurMenu::getId, dto.meun_id)
                    .set(NfsurMenu::getName, dto.name)
                    .set(NfsurMenu::getParent_id, dto.parent_id)
                    .set(NfsurMenu::getLast_update_id, SecurityUtils.getUserId())
                    .set(NfsurMenu::getLast_update_date, new Date())
                    .update();

            if (re <= 0) throw new RuntimeException("update fail");

            return dto.meun_id;
        }
    }

    public MeunDto detail(long meun_id) {
        NfsurMenu first = nfsurMenuRepository.getAll()
                .eq(NfsurMenu::getId, meun_id)
                .first();

        if (first == null) throw new RuntimeException("data not exist");

        MeunDto meunDto = new MeunDto();
        meunDto.meun_id = first.id;
        meunDto.parent_id = first.parent_id;
        meunDto.name = first.name;
        meunDto.create_date = first.create_date;
        meunDto.last_update_date = first.last_update_date;

        return meunDto;
    }

    public void del(long meun_id) {

        //仅演示功能
        SecurityUtils.hostSideOn();

        int re = nfsurMenuRepository.getAll()
                .eq(NfsurMenu::getId, meun_id)
                .delete();

        //仅演示功能
        SecurityUtils.hostSideOff();

        if (re <= 0) throw new RuntimeException("del fail");
    }

    public List<MeunDto> list(MeunListInput input) {

        Queryable<NfsurMenu> all = nfsurMenuRepository.getAll();

        if (!StringUtil.isNullOrEmpty(input.name))
            all = all.like(NfsurMenu::getName, input.name);

        if(input.begin!=null)
            all=all.ge(NfsurMenu::getCreate_date,input.begin);

        if(input.end!=null)
            all=all.le(NfsurMenu::getCreate_date,input.end);

        java.util.List<MeunDto> collect = all.toList().stream().map(t -> {

            MeunDto meunDto = new MeunDto();
            meunDto.meun_id = t.id;
            meunDto.parent_id = t.parent_id;
            meunDto.name = t.name;
            meunDto.create_date = t.create_date;
            meunDto.last_update_date = t.last_update_date;

            return meunDto;
        }).collect(Collectors.toList());

        return collect;
    }

}
