package com.deepoove.swagger.dubbo.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition()
public interface InterfaceServiceTest {
	
	void test(String para, String code);
	
	String login(String name);
	@ApiOperation(nickname="bypwd", value = "")
	String login(String name, String password);

}
