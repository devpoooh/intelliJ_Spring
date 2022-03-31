package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * 회원 목록 조회: GET  /users
 * 회원 등록:     POST /users
 * 회원 조회:     GET  /users/{userId}
 * 회원 수정:     PATCH /users/{userId}
 * 회원 삭제:     DELETE /users/{userId}
 */
//요청매핑 - api
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    //회원 목록 조회
    @GetMapping
    public String user(){
        return "get users";
    }

    //회원 등록
    @PostMapping
    public String addUser(){
        return "post users";
    }

    //회원 조회
    @GetMapping("/{userId}")
    public String findUser(@PathVariable   String userId){
        return "get userId = "+userId;
    }

    //회원수정
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId = "+userId;
    }

    //회원 삭제
    @DeleteMapping("/{userId}")
    public  String deleteUser(@PathVariable String userId){
        return "delete userId = "+userId;
    }
}
