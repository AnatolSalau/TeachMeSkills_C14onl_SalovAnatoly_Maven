import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        //Пример Dependency LookUP
        //Загружаем имплементацию StringHandler -> LowerCaseStringHandler
        //из файла конфигураций StringHandler в папке META-INF.services
        StringHandler stringHandler = ServiceLoader.
                load(StringHandler.class).findFirst().orElse(null);
        stringHandler.print("HeHeHe");
    }
}
