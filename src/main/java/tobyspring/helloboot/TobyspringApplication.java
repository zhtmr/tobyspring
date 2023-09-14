package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TobyspringApplication {

    public static void main(String[] args)
    {
        // 스프링 컨테이너를 만드는 작업
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        // 서블릿 컨테이너를 만들고 실행
        ServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServer webServer = factory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                        /*
                        * 기존에 여기서 bean 작업을 했던 로직 대신 dispatcherServlet 에서 요청 처리를 하게 수정한다.
                        * dispatcherServlet 은 전달된 applicationContext 에서 웹 요청을 처리할 수 있는 클래스를 찾는다. (@RequestMapping, @GetMapping.. 등이 붙은 클래스)
                        * */
                        new DispatcherServlet(applicationContext)

                    ).addMapping("/*");
        });
        webServer.start();
    }

}
