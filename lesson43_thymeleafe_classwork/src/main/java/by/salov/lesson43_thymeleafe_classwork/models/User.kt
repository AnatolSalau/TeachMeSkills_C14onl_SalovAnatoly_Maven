package by.salov.lesson43_thymeleafe_classwork.models

class User {
     companion object {
          const val MAX_LEN = 20
     }
     var name:String = "Anatoly";
     var age:Number = 100;
     var isAdmin:Boolean = true;
}