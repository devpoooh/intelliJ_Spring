package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //[status-line] 응답코드
        resp.setStatus(HttpServletResponse.SC_OK); //200

        //[response-header] 헤더 정보
        resp.setHeader("Content-Type", "text/pain");    //컨텐트타입 지정
        resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");  //캐시를 무력화하겠다
        resp.setHeader("Pragma","no-cache");    //과거버전도 다 없애겠다
        resp.setHeader("my-header","hello");    //http 응답 헤더에 실려나간다

        //[Header 편의 메서드]
        content(resp);
        //[쿠키 편의 메서드]
        cookie(resp);
        //redirect 편의 메서드
        redirect(resp);

        resp.getWriter().write("ok");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8"); //밑에 두개와 같은 의미
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    //쿠키 편의 메서드
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); //밑이랑 같은 코드임
        Cookie cookie = new Cookie("myCookie", "good"); //객체 만들어서 쓰는게 더 편함
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    //redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
