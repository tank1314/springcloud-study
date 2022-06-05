package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.annotation.Decrypt;
import com.tank.springcloud.springbootclient.annotation.Encrypt;
import com.tank.springcloud.springbootclient.model.User;
import com.tank.springcloud.springbootclient.model.base.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/getUser")
    @Encrypt
    public RespBean getUser() {
        User user = new User();
        user.setId(1000L);
        user.setUserName("test_userName");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/user")
    public RespBean addUser(@RequestBody @Decrypt User user){
        System.out.println("user="+user);
        return RespBean.ok("ok",user);
    }
}
