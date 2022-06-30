package listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import services.TimeService;

/*
Создадим листенеры для логирования входящих запросов и создания сессии
 */
@WebListener
public class MyRequestListener implements ServletRequestListener {
    private static int countRequest = 0;
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        TimeService timeService = new TimeService();
        System.out.printf("\n");
        System.out.println("Request " + countRequest + " is entered, time: " + timeService.get());
        countRequest++;
        //У пришедшего события можно получить ServletRequest
        ServletRequest servletRequest = sre.getServletRequest();
        //Преобразовать к httpRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
    }
}
