package com.template.service.domain;

import java.io.Serializable;

public class UserDO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	public UserDO() {

	}

	public UserDO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDO [id=" + id + ", name=" + name + "]";
	}
}
