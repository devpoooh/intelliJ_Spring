package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//json으로 응답하기
@WebServlet(name="responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Content-Type: application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        //데이터넣기
        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //json형식으로 보내기
        //{"username":"kim", "age":20}
        String result = objectMapper.writeValueAsString(helloData);//값을 스트링으로 매핑
        resp.getWriter().write(result);

    }
}
