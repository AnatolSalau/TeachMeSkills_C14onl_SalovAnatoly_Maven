package ServiceLoader;

import java.util.ServiceLoader;

public class MainServiceLoader {
    public static void main(String[] args) {
        //Пример Dependency LookUP
        //Загружаем имплементацию ServiceLoader.StringHandler -> ServiceLoader.LowerCaseStringHandler
        //из файла конфигураций ServiceLoader.StringHandler в папке META-INF.services
        StringHandler stringHandler = ServiceLoader.
                load(StringHandler.class).findFirst().orElse(null);
        stringHandler.print("HeHeHe");
    }
}
