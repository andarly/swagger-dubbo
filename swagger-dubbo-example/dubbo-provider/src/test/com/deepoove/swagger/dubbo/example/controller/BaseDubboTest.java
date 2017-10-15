package com.deepoove.swagger.dubbo.example.controller;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.deepoove.swagger.dubbo.example.api.service.UserService;
import com.deepoove.swagger.dubbo.example.provider.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chenzonglin
 * @date 2017/3/28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application/remote-provider.xml",
        "classpath:application/remote-consumer.xml",
})
@WebAppConfiguration()
@Transactional
public class BaseDubboTest {

    //    @Autowired
//    SecurityManager securityManager;
    protected Logger log;


    @Autowired
    UserServiceImpl userService;

    @Before

    public void setUp() throws Exception {
//        SecurityUtils.setSecurityManager(securityManager);
//        Login login=new Login();
//        SysUser user=new SysUser();
//        user.setName("junit");
//        user.setId("-1");
//        login.setUser(user);
//        WebUtil.setCurrentUser(login);
        log = LoggerFactory.getLogger(this.getClass());
    }

    @After
    public  void setdown(){
        ProtocolConfig.destroyAll();
    }

    @Test
    public void name() throws Exception {

//        sysUserExtSer.login(null, null, null);
//        sysUserService.login(null, null, null);


        userService.get("");
    }


}
