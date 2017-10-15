package com.deepoove.swagger.dubbo.example.provider;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.deepoove.swagger.dubbo.example.api.service.PushService;

import com.deepoove.swagger.dubbo.example.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

public class PushServiceImpl implements PushService{


	@ApiOperation(value = "推送", notes = "推送消息至指定帐号")
	@Override
	public String push(@ApiParam(value="帐号") String account) {
		return "中文字符串";
	}

}
