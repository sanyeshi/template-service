package com.template.service.mapper;

import com.template.service.domain.UserDO;

public interface UserMapper {
	public UserDO findUserById(long id);

	public int insertUser(UserDO userDO);
}
