package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/springmvc/request-handler") //1. 등록된 빈 이름으로 url요청이 오면 해달 컨트롤러를 실행한다.
public class MyHttpRequestHandler implements HttpRequestHandler {   //2. 어댑터를 찾는데 HttpRequestHandler가 2순위이다.
    @Override
    // 실행
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
