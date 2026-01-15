/**
 * 
 */
package com.ngins.spring.jpa.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ngins.spring.jpa.entity.mariadb.PageAction;
import com.ngins.spring.jpa.repository.PageActionRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class PageActionService {
	private final PageActionRepository pageActionRepository;
	
//	public PageActionService (PageActionRepository pageActionRepository) {
//		super();
//		this.pageActionRepository = pageActionRepository;
//	}

    public List<PageAction> findAll() {
        return pageActionRepository.findAll();
    }

    public PageAction save(PageAction post) {
        return pageActionRepository.save(post);
    }

    public Optional<PageAction> findById(Long id) {
        return pageActionRepository.findById(id);
    }

    public void delete(Long id) {
    	pageActionRepository.deleteById(id);
    }
}
