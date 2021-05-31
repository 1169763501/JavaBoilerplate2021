package com.nbnfsoft.admin.config;

import java.util.Optional;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.nbnfsoft.admin.common.constant.TenantConstants;
import com.nbnfsoft.admin.framework.security.LoginUser;
import com.nbnfsoft.admin.utils.FriendlyException;
import com.nbnfsoft.admin.utils.SecurityUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.nbnfsoft.admin.repository")
public class MybatisPlusConfig {
    @Bean
    public OracleKeyGenerator oracleKeyGenerator() {
        return new OracleKeyGenerator();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                LoginUser loginUser = SecurityUtils.getLoginUser();
                Optional<Long> orgId = Optional.ofNullable(loginUser.getOrgId());
                if (!orgId.isPresent()) {
                    throw new FriendlyException("用户信息错误,机构ID不能为空");
                }
                return new LongValue(orgId.get());
            }

            @Override
            public String getTenantIdColumn() {
                return TenantConstants.ORG_ID;
            }

            // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
            @Override
            public boolean ignoreTable(String tableName) {

                if(SecurityUtils.getAuthentication()==null) return true;

                if(((NFUPAuthenticationToken)SecurityUtils.getAuthentication()).hostSide==true) return true;

                if (!TenantConstants.ORG_ID_CHECK_TABLE.contains(tableName)) {
                    return true;
                }
                return false;
            }
        }));
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
