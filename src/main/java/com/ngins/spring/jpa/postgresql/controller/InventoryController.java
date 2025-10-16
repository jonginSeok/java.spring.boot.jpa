package com.ngins.spring.jpa.postgresql.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 컨트롤러 예시:
 * @return
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @GetMapping("/items")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'QUERY')")
  public List<ItemDto> list(...) { ... }

  @PostMapping("/items")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'SAVE')")
  public ItemDto save(...) { ... }

  @GetMapping("/items/export")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'EXPORT_XLS')")
  public ResponseEntity<Resource> exportXls(...) { ... }

  @PostMapping("/items/import")
  @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'IMPORT_XLS')")
  public ImportResult importXls(...) { ... }


}
