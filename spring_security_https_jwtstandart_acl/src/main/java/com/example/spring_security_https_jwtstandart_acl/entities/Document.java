package com.example.spring_security_https_jwtstandart_acl.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

@Entity
@Table(name = "documents")
@SequenceGenerator(sequenceName = "documents_id_seq", name = "documents_id_seq", allocationSize = 1)
public class Document {
      @Id
      @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "documents_id_seq")
      @Column(nullable = false)
      private Long id;

      @Column(nullable = false)
      private String content;

      public Document() {
      }

      public Document(String content) {
            this.content = content;
      }
}
