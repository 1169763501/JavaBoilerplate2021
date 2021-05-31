package com.nbnfsoft;

import com.nbnfsoft.admin.AdminApplication;
import com.nbnfsoft.admin.framework.redis.RedisCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 13:51 2019-11-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
@WebAppConfiguration
public class BaseJunit {

    protected MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    RedisCache redisCache;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getDeptList() {
    }

    @Test
    public void getEmpList() {
    }

    @Test
    public void redisTest() {
    }
}

