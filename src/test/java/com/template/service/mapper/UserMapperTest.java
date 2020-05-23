package com.template.service.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.template.service.BaseTest;
import com.template.service.domain.UserDO;
import com.template.service.mapper.UserMapper;

public class UserMapperTest extends BaseTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void findUserByIdTest() {
		UserDO user = userMapper.findUserById(1);
		System.out.println(user);
	}
}
