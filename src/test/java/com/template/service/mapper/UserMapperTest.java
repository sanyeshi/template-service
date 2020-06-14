package com.template.service.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

	@Test
	@Transactional
	@Rollback()
	public void insertUserTest() {
		UserDO userDO = new UserDO();
		userDO.setName("ssl");
		int rows = userMapper.insertUser(userDO);
		Assert.assertTrue(rows == 1);
	}
}
