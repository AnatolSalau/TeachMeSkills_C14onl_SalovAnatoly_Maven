package exceptions;

public class UserRuntimeException extends RuntimeException{
      private int statusCode;
      public UserRuntimeException(int statusCode, String message) {
            super(message) ;
            this. statusCode = statusCode;
      }
}
