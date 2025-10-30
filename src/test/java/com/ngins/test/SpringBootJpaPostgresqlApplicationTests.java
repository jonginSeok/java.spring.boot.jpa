package com.ngins.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngins.spring.jpa.postgresql.jpa.entity.Permission;
import com.ngins.spring.jpa.postgresql.jpa.repository.PermissionRepository;
import com.ngins.spring.jpa.postgresql.service.PermissionService;

@SpringBootTest
class SpringBootJpaPostgresqlApplicationTests {

	@Autowired
	private PermissionService permissionService;

	@SuppressWarnings("unused")
	@Autowired
	private PermissionRepository permissionRepository;

	@Test
	void contextLoads() {

		String actionKey = "DELETE";
		String resourceKey = "inventory";
		Long permissionId = 1L;

		Permission ps = new Permission();

		ps.setActionKey(actionKey);
		ps.setResourceKey(resourceKey);
		ps.setDescription("재고 삭제");

//        permissionService.save(ps);
		Optional<Permission> per = permissionService.findById(permissionId);

		assertThat(per).isNotEmpty();

	}

}
