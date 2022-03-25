package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 등록
@WebServlet(name = "helloServlet", urlPatterns = "/hello") //name:서블릿 명 urlPattern: 해당 경로로 오면 실행한다.
public class HelloServlet extends HttpServlet {

    //함수가 실행되면 service 메서드가 호출이 된다. (override -> ctrl+o)
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        //쿼리스트링 값 가져오기 /hello?username=kim
        String username = req.getParameter("username");
        System.out.println("username = " + username); //username=kim

        //응답하기
        resp.setContentType("text/plain"); //일반 텍스트를 의미
        resp.setCharacterEncoding("utf-8"); //문자 인코딩 정보
        resp.getWriter().write("hello "+username); //해당 페이지에 글을 쓰게 된다.

        //임시저장소기능: 해당 요청이 끝날 때(응답할 때)까지 유지되는 저장소
//        req.setCharacterEncoding(name,value); //값 저장
//        req.getAttribute(name); //값 조회
    }
}
