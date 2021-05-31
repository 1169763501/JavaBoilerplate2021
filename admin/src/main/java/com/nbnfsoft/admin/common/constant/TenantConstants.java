package com.nbnfsoft.admin.common.constant;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 9:37 2020-12-30
 */
public interface TenantConstants {

    String ORG_ID="ORG_ID";
    /**
     * 租户orgId字段表过滤
     */
    Set<String> ORG_ID_CHECK_TABLE = Sets.newHashSet("NFSUR_MENU");
}
