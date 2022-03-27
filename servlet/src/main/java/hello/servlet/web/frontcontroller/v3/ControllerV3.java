package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //v2의 http요청 응답을 없앴다.
    ModelView process(Map<String,String> paramMap);
}
