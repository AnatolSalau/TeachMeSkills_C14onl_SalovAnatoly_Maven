package com.example.spring_security_https_jwtstandart_acl.dto;

import java.util.Objects;

public class ErrorDTO {
      private int statusCode;
      private String messages;

      public ErrorDTO() {
      }

      public ErrorDTO(int statusCode, String messages) {
            this.statusCode = statusCode;
            this.messages = messages;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ErrorDTO)) return false;
            ErrorDTO errorDTO = (ErrorDTO) o;
            return statusCode == errorDTO.statusCode && messages.equals(errorDTO.messages);
      }

      @Override
      public int hashCode() {
            return Objects.hash(statusCode, messages);
      }

      public int getStatusCode() {
            return statusCode;
      }

      public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
      }

      public String getMessages() {
            return messages;
      }

      public void setMessages(String messages) {
            this.messages = messages;
      }
}
