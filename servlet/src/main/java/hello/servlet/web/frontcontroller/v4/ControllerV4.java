package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    //v3: ModelView process(Map<String,String> paramMap); //경로와 모델을 같이 넘겼다.

    /**
     *
     * @param paramMap
     * @param model
     * @return viewName //view 경로
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
