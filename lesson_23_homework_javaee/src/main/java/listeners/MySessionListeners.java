package listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MySessionListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().getSessionTimeout();
        sce.getServletContext().getSessionCookieConfig();

    }
}
