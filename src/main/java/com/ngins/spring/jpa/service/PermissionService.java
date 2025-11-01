/**
 * 
 */
package com.ngins.spring.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngins.spring.jpa.entity.postgresql.Permission;
import com.ngins.spring.jpa.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class PermissionService {
	private final PermissionRepository permissionRepository;

//	public PermissionService(PermissionRepository permissionRepository) {
//		super();
//		this.permissionRepository = permissionRepository;
//	}

	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Transactional
	public Permission save(Permission post) {
		return permissionRepository.save(post);
	}

	public Optional<Permission> findById(Long id) {
		return permissionRepository.findById(id);
	}

	public void delete(Long id) {
		permissionRepository.deleteById(id);
	}
}
