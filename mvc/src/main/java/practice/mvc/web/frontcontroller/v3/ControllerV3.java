package practice.mvc.web.frontcontroller.v3;

import practice.mvc.web.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //v2의 http요청 응답을 없앴다.
    ModelView process(Map<String,String> paramMap);
}
