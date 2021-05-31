package com.nbnfsoft.admin.service;

import com.nbnfsoft.admin.common.constant.Constants;
import com.nbnfsoft.admin.framework.redis.RedisCache;
import com.nbnfsoft.admin.repository.SysParametersRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 17:04 2020-12-18
 */
@Service
public class SysParametersService {
    @Autowired
    private SysParametersRepository sysParametersRepository;
    @Autowired
    private RedisCache redisCache;

    /**
     * 全该方法获取的是局参数获取
     *
     * @param code
     * @param cache
     * @return
     */
    public String selectGlobalValueByCode(String code, boolean cache) {
        if (cache) {
            String value = redisCache.getCacheObject(Constants.GLOBAL_PARAM_CODE + code);
            if (StringUtils.isEmpty(value)) {
                value = sysParametersRepository.selectGlobalValueByCode(code);
                redisCache.setCacheObject(Constants.GLOBAL_PARAM_CODE + code, value);
            }
            return value;
        }
        return sysParametersRepository.selectGlobalValueByCode(code);
    }

}
