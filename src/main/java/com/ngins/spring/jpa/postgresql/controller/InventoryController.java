package com.ngins.spring.jpa.postgresql.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 컨트롤러 예시:
 * 
 * @return
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
  
  // @GetMapping("/items")
  // @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'QUERY')")
  // public List<Menu> list(...) { ... }

  // @PostMapping("/items")
  // @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'SAVE')")
  // public Menu save(...) { ... }

  // @GetMapping("/items/export")
  // @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'EXPORT_XLS')")
  // public ResponseEntity<Resource> exportXls(...) { ... }

  // @PostMapping("/items/import")
  // @PreAuthorize("@authz.canPerform(authentication, 'PAGE_INVENTORY_LIST', 'IMPORT_XLS')")
  // public ImportResult importXls(...) { ... }


}
