package com.example.spring_security_https_jwtstandart_acl.exceptions;

public class CustomRuntimeException extends RuntimeException{
      private int statusCode;
      public CustomRuntimeException(int statusCode, String message) {
            super(message) ;
            this.statusCode = statusCode;
      }

      public int getStatusCode() {
            return statusCode;
      }

      public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
      }
}
