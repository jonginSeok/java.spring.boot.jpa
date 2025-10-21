package com.ngins.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ngins.spring.jpa.postgresql.jpa.entity.Menu;

@SpringBootTest
class SpringBootJpaPostgresqlApplicationTests {

	@Test
	void contextLoads() {
		
		Menu menu = new Menu();
		
		List<Menu> children =  menu.getChildren();
		
		System.out.println(children);
	}

}
