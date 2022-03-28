package practice.mvc.web.frontcontroller.v3.controller;


import practice.mvc.web.ModelView;
import practice.mvc.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        //3-2 전체 경로가 아닌 논리적인 경로만 넣는다.
        return new ModelView("new-form");
    }
}
