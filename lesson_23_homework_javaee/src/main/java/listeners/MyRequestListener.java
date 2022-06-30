package listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

public class MyRequestListener implements ServletRequestListener {
    //Request уничтожается
    //Приходят не requests а события!
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("-------------REQUEST DESTROY--------------------");
    }

    //Request инициализируется
    //Приходят не requests а события!
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("-------------REQUEST DESTROY--------------------");

        //У пришедшего события можно получить ServletRequest
        ServletRequest servletRequest = sre.getServletRequest();
        //Преобразовать к httpRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
    }
}
