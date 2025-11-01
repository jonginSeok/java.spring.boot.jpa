/**
 * 
 */
package com.ngins.spring.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ngins.spring.jpa.entity.mariadb.Page;
import com.ngins.spring.jpa.repository.PageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class PageService {
	private final PageRepository pageRepository;

//    public PageService(PageRepository pageRepository) {
//		super();
//		this.pageRepository = pageRepository;
//	}

	public List<Page> findAll() {
        return pageRepository.findAll();
    }

    @Transactional
    public Page save(Page post) {
        return pageRepository.save(post);
    }

    public Optional<Page> findById(Long id) {
        return pageRepository.findById(id);
    }

    public void delete(Long id) {
    	pageRepository.deleteById(id);
    }
}
