package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@MyComponent
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
