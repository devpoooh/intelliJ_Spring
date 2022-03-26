package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 MVC의 회원가입
@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; //jsp 경로
        //requestDispatcher: controller -> view 사용한다.
        //그러므로 뷰의 jsp는 WEB-INF안에 넣는다.(외부에서 호출하지 못함)
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); //해당 경로로 이동할거다
        dispatcher.forward(req,resp); //서블릿에서 다시 jsp호출
    }
}
