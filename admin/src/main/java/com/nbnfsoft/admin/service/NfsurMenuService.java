package com.nbnfsoft.admin.service;

import com.nbnfsoft.admin.domain.dto.MeunDto;
import com.nbnfsoft.admin.domain.input.MeunListInput;
import com.nbnfsoft.admin.manager.NfsurMenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NfsurMenuService {

    @Autowired
    NfsurMenuManager nfsurMenuManager;

    public long createOrUpdate(MeunDto dto)
    {
        return nfsurMenuManager.createOrUpdate(dto);
    }

    public MeunDto detail(long meun_id)
    {
        return nfsurMenuManager.detail(meun_id);
    }

    public void del(long meun_id)
    {
        nfsurMenuManager.del(meun_id);
    }

    public List<MeunDto> list(MeunListInput input)
    {
       return nfsurMenuManager.list(input);
    }


}
