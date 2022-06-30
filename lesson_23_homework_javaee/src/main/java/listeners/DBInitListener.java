package listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//ServletContextListener - листенер который запускается сразу после старта приложения
public class DBInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("--------DBInitListener Init--------");
        //Получаем контекст
        ServletContext servletContext =sce.getServletContext();
        //Получаем сохраненные в XML контекст параметры
        String user = servletContext.getInitParameter("db.user");
        String pass = servletContext.getInitParameter("db.pass");
        System.out.println("Connection to DB: user=" +user + " pass=" +pass
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("--------DBInitListener Destroy--------");
    }
}
