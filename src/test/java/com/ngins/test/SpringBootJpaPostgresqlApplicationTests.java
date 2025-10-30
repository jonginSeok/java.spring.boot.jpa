package com.ngins.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngins.spring.jpa.postgresql.jpa.entity.Permission;
import com.ngins.spring.jpa.postgresql.service.PermissionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SpringBootJpaPostgresqlApplicationTests {

	@Autowired
	private PermissionService permissionService;

//	@SuppressWarnings("unused")
//	@Autowired
//	private PermissionRepository permissionRepository;

	@Test
	void contextLoads() {

		// 스프링 컨텍스트가 정상적으로 로딩되는지 확인

		Long permissionId = 6L;

		Permission ps = new Permission();

		ps.setActionKey("DELETE");
		ps.setResourceKey("inventory");
		ps.setDescription("재고 삭제");

		permissionService.save(ps);

		Optional<Permission> per = permissionService.findById(permissionId);
		System.out.println("per:" + per.get().toString());

		assertThat(per).isNotEmpty();

	}

}
