package com.example.spring_security_https_jwtstandart_acl.controllers;

import com.example.spring_security_https_jwtstandart_acl.entities.Document;
import com.example.spring_security_https_jwtstandart_acl.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
      @Autowired
      private DocumentRepository repository;

/*      @Autowired
      private PermissionService permissionService;*/

      @GetMapping("/api/v1/document")
      public List<Document> getAll() {
            System.out.println("________________GET_______________ALL___________________");
            return repository.findAll();
      }
      @GetMapping("/api/v1/document/{id}")
      public Document getById(@PathVariable("id") Integer id) {
            return repository.getById(id);
      }
      @PreAuthorize("hasPermission(#document, 'WRITE')")
      @PutMapping("/api/v1/document/{id}")
      public Document edit(@PathVariable("id") Long id, @RequestBody Document document) {
            document.setId(id);
            return repository.save(document);
      }
/*      @PreAuthorize("hasAuthority('ROLE_ADMIN')")
      @PostMapping("/document")
      public Document post(@RequestBody Document document, Authentication authentication) {
            permissionService.addPermissionForUser(document, BasePermission.WRITE, authentication.getName());
            permissionService.addPermissionForUser(document, BasePermission.READ, authentication.getName());
            return repository.save(document);
      }*/
}
