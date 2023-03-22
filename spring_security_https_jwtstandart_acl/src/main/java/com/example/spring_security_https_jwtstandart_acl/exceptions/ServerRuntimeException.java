package com.example.spring_security_https_jwtstandart_acl.exceptions;

public class ServerRuntimeException extends RuntimeException{
      private int statusCode;
      public ServerRuntimeException(int statusCode, String message) {
            super(message) ;
            this.statusCode = statusCode;
      }
}
