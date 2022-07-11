package listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;


import java.util.HashMap;
import java.util.Map;

@WebListener
public class MySessionListeners implements ServletRequestListener {
    private static int count = 0;
    private static int countSession = 0;
    private static Map<String, String> session_map = new HashMap<>();

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println();
        System.out.println("MySessionListeners " + count + " destroyed");
        count++;
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        System.out.println((Map<String, String>) httpServletRequest.getServletContext().getAttribute("SESSIONS_MAP"));
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println();
        System.out.println("MySessionListeners " + count + " initialize");
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        //Create session
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setMaxInactiveInterval(1);
        //Find out created session new or not
        System.out.println("httpSession.isNew() = " + httpSession.isNew());
        //Создадим в servlet context глобальный счетчик map ( порядковый номер создания сессии : id сессии)
        ServletContext servletContext = httpServletRequest.getServletContext();
        servletContext.setAttribute("SESSIONS_MAP", session_map);
        //Если сессия новая будем добавлять ее в глобальный счетчик
        if (httpSession.isNew()) {
            session_map.put(String.valueOf(countSession), httpSession.getId());
            servletContext.setAttribute("SESSIONS_MAP", session_map);
            countSession++;
        }
    }
}
