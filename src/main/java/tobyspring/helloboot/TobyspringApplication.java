package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class TobyspringApplication {
    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args)
    {
        // 스프링 컨테이너를 초기화하면서 서블릿 컨테이너도 초기화하게 수정
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() { // 오버라이드를 통해 템플릿메서드 패턴 훅 재정의
                super.onRefresh();

                // 서블릿 컨테이너를 만들고 실행
                ServletWebServerFactory factory = new TomcatServletWebServerFactory();
                WebServer webServer = factory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };

//        applicationContext.registerBean(HelloController.class);
        applicationContext.register(TobyspringApplication.class);
        applicationContext.refresh();

    }

}
