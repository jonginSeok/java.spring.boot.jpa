/**
 * 
 */
package com.ngins.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ngins.spring.jpa.postgresql.jpa.entity.Page;
import com.ngins.spring.jpa.postgresql.jpa.repository.PageRepository;
import com.ngins.spring.jpa.postgresql.service.PageService;

/**
 * 
 */
class PageServiceTest {

	@Mock
	private PageRepository pageRepository;

	@InjectMocks
	private PageService pageService;

	public PageServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void savePageTest() {
		Page page = new Page(null, "제목", "내용");
		when(pageRepository.save(page)).thenReturn(new Page(3L, "3제목", "3내용"));

		Page saved = pageService.save(page);
		
		System.out.println("saved:" + saved.getName());

		assertThat(saved.getId()).isEqualTo(3L);
	}
}
