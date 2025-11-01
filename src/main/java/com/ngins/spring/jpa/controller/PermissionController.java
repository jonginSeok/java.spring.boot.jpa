package com.ngins.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ngins.spring.jpa.entity.postgresql.Permission;
import com.ngins.spring.jpa.service.PermissionService;

/**
 * 
 */
@Controller
//@RequiredArgsConstructor
@RequestMapping("/Permissions")
public class PermissionController {

	private final PermissionService permissionService;

	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("Permissions", permissionService.findAll());
		return "Permission/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("post", new Permission());
		return "Permission/form";
	}

	@PostMapping
	public String create(Permission post) {
		permissionService.save(post);
		return "redirect:/Permissions";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("post", permissionService.findById(id).orElseThrow());
		return "Permission/detail";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		permissionService.delete(id);
		return "redirect:/Permissions";
	}
}
