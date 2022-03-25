package hello.servlet.basic;

import org.springframework.aop.scope.ScopedProxyUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

//request 정보에 대해서 알아보기
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        extracted(req); //request 정보
        printHeaders(req); //header 정보
        printHeaderUtils(req); //쿠키정보
        printEtc(req); //기타 정보(http 메시지 정보 아님)
    }

    private void extracted(HttpServletRequest req) {
        System.out.println("--- REQUEST-LINE start ---");

        System.out.println("req.getMethod() = " + req.getMethod()); //메서드 (get, post)
        System.out.println("req.getProtocol() = " + req.getProtocol()); //http/1.1
        System.out.println("req.getScheme() = " + req.getScheme()); //http
        //http://localhost:8080/request-header
        System.out.println("req.getRequestURL() = " + req.getRequestURL()); //전체 url
        System.out.println("req.getRequestURI() = " + req.getRequestURI()); //끝 주소/request-header

        //쿼리 스트링
        System.out.println("req.getQueryString() = " + req.getQueryString()); //username=hi
        System.out.println("req.isSecure() = " + req.isSecure()); //https 사용 유무 (현 false)
        System.out.println("--- REQUEST-LINE - end --- ");
        System.out.println();
    }

    //Header 정보
    private void printHeaders(HttpServletRequest req){
        System.out.println("--- Headers - start ---");

        //header name 출력
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println("headername: " + s); //header name 출력
        }

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //쿠키 정보
    private void printHeaderUtils(HttpServletRequest req){
        System.out.println("--- Header 편의 조회 start ---");

        System.out.println("[ Host 편의 조회 ]");
        System.out.println("req.getServerName() = " + req.getServerName()); //서버명=localhost
        System.out.println("req.getServerPort() = " + req.getServerPort()); //서버포트=8080
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        //언어 정보: ko, en-US,en,ko-KR
        req.getLocales().asIterator()
                        .forEachRemaining(locale -> System.out.println("locale = "+locale));
        System.out.println("req.getLocale() = " + req.getLocale()); //가장 위에 있는 언어 꺼내줌:ko
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if(req.getCookies() != null){
            for (Cookie cookie : req.getCookies()) {
                System.out.println("cookie.getName() = " + cookie.getName());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]"); //바디정보
        System.out.println("req.getContentType() = " + req.getContentType()); //타입
        System.out.println("req.getContentLength() = " + req.getContentLength()); //길이
        System.out.println("req.getCharacterEncoding() = " + req.getCharacterEncoding()); //인코딩 정보

        System.out.println("--- Header 편의 조회 end ---");
    }

    private void printEtc(HttpServletRequest req){
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]"); //요청이 온 것에 대한 정보
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost()); //0:0:0:0:0:0:0:1
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr()); //0:0:0:0:0:0:0:1
        System.out.println("req.getRemotePort() = " + req.getRemotePort()); //65008
        System.out.println();

        System.out.println("[Local 정보]"); //현재 서버에 대한 정보
        System.out.println("req.getLocalName() = " + req.getLocalName()); //0:0:0:0:0:0:0:1
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr()); //0:0:0:0:0:0:0:1
        System.out.println("req.getLocalPort() = " + req.getLocalPort()); //local port: 8080

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
