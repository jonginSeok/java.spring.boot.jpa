package com.ngins.spring.jpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ngins.spring.jpa.entity.mariadb.PageAction;
import com.ngins.spring.jpa.service.PageActionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/PageActions")
public class PageActionController {

	private final PageActionService pageActionService;
	
//	public PageActionController(PageActionService pageActionService) {
//		this.pageActionService = pageActionService;
//	}

	@GetMapping
	public String list(Model model) {
		
		List<PageAction> list = pageActionService.findAll();
		
		log.debug("list: ${}", list);
		
		model.addAttribute("PageActions", list);
		return "PageAction/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("post", new PageAction());
		return "PageAction/form";
	}

	@PostMapping
	public String create(PageAction post) {
		pageActionService.save(post);
		return "redirect:/PageActions";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("post", pageActionService.findById(id).orElseThrow());
		return "PageAction/detail";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		pageActionService.delete(id);
		return "redirect:/PageActions";
	}
}
