package com.template.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.api.UserService;
import com.template.api.dto.UserDTO;
import com.template.service.domain.UserDO;
import com.template.service.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOG=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO getUser(Long id) {
		LOG.info("getUser id={}",id);
		UserDO userDO=userMapper.findUserById(id);
		UserDTO userDTO=new UserDTO();
		BeanUtils.copyProperties(userDO, userDTO);
		return userDTO;
	}
}
