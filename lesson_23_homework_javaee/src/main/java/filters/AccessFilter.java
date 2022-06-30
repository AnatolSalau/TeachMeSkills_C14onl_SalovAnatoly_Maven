package filters;

import exceptions.AccessFilterException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*Создать фильтр, который будет отклонять все запросы
если в реквест хидере нет спец параметра (но разрешать при этом все get запросы)*/
//Регистрируем фильтр на все url
@WebFilter(value = "/*")
public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Преобразуем servletRequest к HttpservletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //Перечисление с именами хидеров
        Enumeration<String> headerNamesEnum = httpServletRequest.getHeaderNames();
        //Создаем map чтобы в ней нормально хранить хидеры ключ-значение
        Map<String,String> allHeadersMap = new HashMap<>();
        //заполняем
        while (headerNamesEnum.hasMoreElements()) {
            String key = headerNamesEnum.nextElement();
            String value = httpServletRequest.getHeader(key);
            allHeadersMap.put(key, value);
        }
        //Печатаем в консоле
        System.out.println("ALL HEADERS:");
        allHeadersMap.forEach((key, value) -> {
            System.out.println(key + " :");
            System.out.println(value);
        });
        //Ключ и значение параметра хранятся в xml как контекстный параметр
        // user = onlyGet
        String userValue = httpServletRequest.getServletContext().getInitParameter("user");
        System.out.println("CONTEXT PARAMETERS:");
        System.out.println("value:" + userValue);
        //Проверка есть ли такой параметр в хидере
        if (allHeadersMap.containsKey("user")  && allHeadersMap.get("user").equals(userValue) ) {
            System.out.println("AССESS IS ALLOWED");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println("Have not head: user = " + userValue);
            //Проверяем GET или не GET
            if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            System.out.println("Key or value in headers wrong");
            throw new AccessFilterException("Key or value in headers wrong");
        }
    }
}