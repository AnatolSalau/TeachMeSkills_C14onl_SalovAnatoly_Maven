package filters;

import exceptions.AccessFilterException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
//Порядок вызова фильтров не учитывается
//Регистрируем фильтр на все url нашего приложения
//@WebFilter("/*")
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-------------------INIT------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String user = servletRequest.getParameter("user");
        if (  user == null) {
            throw new AccessFilterException("user value is wrong");
        }
        switch (user) {
            case "admin" -> {
                System.out.println("Is admin");
                //Filter chain реализует цепочку вызовов -> Фильтры вызываются все поочереди
                //Поэтому необходимо передать request and response дальше в следующей фильтр
                //если не будет пройден хоть один фильтр -> вызовется какая то ошибка
                filterChain.doFilter(servletRequest, servletResponse);
            }
            default -> {
                    System.out.println("It is not admin");
                    //Преобразуем servletRequest(который используется в Filter) к
                    // HttpServletRequest (который используется в servlets)
                    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                    //Получаем название метода
                    String method = httpServletRequest.getMethod();
                    switch (method.toUpperCase()) {
                        case ("GET") -> {
                            System.out.println("Method is get but user is not admin");
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                        default -> {
                            System.out.println("It is not get and user is not admin");
                        }
                    }
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("-------------------DESTROY------------------");

    }
}
