package com.nbnfsoft.admin.framework.security.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.nbnfsoft.admin.common.constant.Constants;
import com.nbnfsoft.admin.domain.dto.HospitalDto;
import com.nbnfsoft.admin.framework.redis.RedisCache;
import com.nbnfsoft.admin.framework.security.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token验证处理
 *
 * @author louyi
 */
@Component
public class TokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌有效期（默认120分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        try {
            // 获取请求携带的令牌
            String token = getToken(request);
            if (StringUtils.isNotEmpty(token)) {
                JSONObject jsonObject = redisCache.getCacheObject(Constants.LOGIN_TOKEN_KEY + token);
                if (jsonObject != null) {
                    LoginUser loginUser = new LoginUser();
                    JSONObject payload = jsonObject.getJSONObject("Payload");
                    loginUser.setToken(payload.getString("token"));
                    loginUser.setUserId(payload.getLongValue("user_id"));
                    loginUser.setEmpCode(payload.getString("emp_code"));
                    loginUser.setEmpName(payload.getString("emp_name"));
                    loginUser.setHospitalId(payload.getLongValue("hospital_id"));
                    loginUser.setOpenId(payload.getString("open_id"));
                    loginUser.setPassword(payload.getString("password"));
                    loginUser.setOrgId(payload.getLongValue("org_id"));
                    loginUser.setLoginTime(payload.getLongValue("loginTime"));
                    loginUser.setExpireTime(payload.getLongValue("expireTime"));
                    loginUser.setSysAdmin(payload.getString("is_admin"));
                    List<HospitalDto> hospitalDtoList = Lists.newArrayList();
                    JSONArray array = payload.getJSONArray("hospitals");
                    if (array != null && !array.isEmpty()) {
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject h = array.getJSONObject(i);
                            HospitalDto dto = new HospitalDto();
                            dto.setId(h.getLongValue("id"));
                            dto.setHospitalName(h.getString("hospital_name"));
                            dto.setHospitalCode(h.getString("hospital_code"));
                            hospitalDtoList.add(dto);
                        }
                    }
                    loginUser.setHospitals(hospitalDtoList);
                    return loginUser;
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        redisCache.setCacheObject(getTokenKey(loginUser.getToken()), buildTokenValue(loginUser), expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        return request.getHeader(header);
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 兼容老的OA
     *
     * @param loginUser
     * @return
     */
    private JSONObject buildTokenValue(LoginUser loginUser) {
        JSONObject payload = new JSONObject();
        payload.put("token", loginUser.getToken());
        payload.put("user_id", loginUser.getUserId());
        payload.put("emp_code", loginUser.getEmpCode());
        payload.put("emp_name", loginUser.getEmpName());
        payload.put("password", loginUser.getPassword());
        payload.put("org_id", loginUser.getOrgId());
        payload.put("hospital_id", loginUser.getHospitalId());
        payload.put("open_id", loginUser.getOpenId());
        payload.put("org_name", loginUser.getOrgName());
        payload.put("loginTime", loginUser.getLoginTime());
        payload.put("expireTime", loginUser.getExpireTime());
        payload.put("is_admin", loginUser.getSysAdmin());
        //老系统用的，兼容
        payload.put("system", new JSONArray());

        JSONArray array = new JSONArray();
        loginUser.getHospitals().forEach(i -> {
            JSONObject h = new JSONObject();
            h.put("id", i.getId());
            h.put("hospital_name", i.getHospitalName());
            h.put("hospital_code", i.getHospitalCode());
            array.add(h);
        });
        payload.put("hospitals", array);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Payload", payload.toJSONString());
        jsonObject.put("Type", Constants.OA_TYPE);
        return jsonObject;
    }

}
