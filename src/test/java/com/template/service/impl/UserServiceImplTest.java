package com.template.service.impl;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.template.api.UserService;
import com.template.api.dto.UserDTO;
import com.template.common.exception.JsonSerializationException;
import com.template.common.util.JsonUtil;
import com.template.service.BaseTest;

import io.prometheus.client.exporter.HTTPServer;

public class UserServiceImplTest extends BaseTest {

	protected HTTPServer server;
	@Autowired
	private UserService userService;

	@Before
	public void init() {
		try {
			server = new HTTPServer(8080);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@After
	public void destroy() {
		if (server != null) {
			server.stop();
		}
	}

	@Test
	public void getUserTest() throws JsonSerializationException {
		UserDTO userDTO = userService.getUser(1L);
		Assert.assertTrue(userDTO != null);
		System.out.println(JsonUtil.prettyPrint(JsonUtil.toJson(userDTO)));
		try {
			Thread.sleep(1000 * 180);
		} catch (Exception e) {

		}
	}
}
