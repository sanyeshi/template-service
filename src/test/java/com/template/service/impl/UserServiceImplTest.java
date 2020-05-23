package com.template.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.template.api.UserService;
import com.template.api.dto.UserDTO;
import com.template.common.exception.JsonSerializationException;
import com.template.common.util.JsonUtil;
import com.template.service.BaseTest;

public class UserServiceImplTest extends BaseTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void getUserTest() throws JsonSerializationException {
		UserDTO userDTO=userService.getUser(1L);
		Assert.assertTrue(userDTO!=null);
		System.out.println(JsonUtil.prettyPrint(JsonUtil.toJson(userDTO)));
	}
}
