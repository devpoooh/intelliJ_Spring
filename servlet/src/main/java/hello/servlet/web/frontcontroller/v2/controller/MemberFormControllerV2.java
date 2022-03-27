package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*v1 버전
        String viewPath = "/WEB-INF/views/new-form.jsp"; //jsp 경로
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); //해당 경로로 이동할거다
        dispatcher.forward(req,resp);
        */

        //requestdispatche와 포워드를 myview에서 해준다.
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
