//package com.example.cookeryket_sb.controller;
//
//import com.example.cookeryket_sb.entity.Userprofile;
//import jakarta.annotation.PostConstruct;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController  // 스프링이 알아서 이 클래스를 컨트롤러로 인식함
//public class UserProfileController {
//    // userprofile을 담는 map 선언
//    private Map<String, Userprofile> userMap;
//
//    @PostConstruct  // 스프링이 userprofilecontroller 클래스 인스턴스를 만들고 그 직후 호출함
//    public void init(){  // 데이터 초기화
//        userMap=new HashMap<String, Userprofile>();  // usermap에 3개의 데이터를 맵으로 갖고 있음
//        userMap.put("1",new Userprofile("1","홍길동","111-1111", "서울시"));
//        userMap.put("2",new Userprofile("2","김민지","111-2222", "천안시"));
//        userMap.put("3",new Userprofile("3","이유리","111-3333", "전주시"));
//    }
//
//    // 클라이언트에서 호출한 GetMapping의 {id} 부분을 @PathVariable로 인식하고, 그걸 String id 파라미터로 입력해서
//    // getUserProfile api를 호출하게 된다.
//    @GetMapping("/user/{id}")
//    public Userprofile getUserProfile(@PathVariable("id") String id){
//        return userMap.get(id);  // 미리 정의한 userMap에서 요청한 id에 해당하는 userprofile 정보를 가지고 와서 리턴해줌
//    }
//
//    // usermap이 가지고 있는 3개의 항목에서 키, 3개의 value를 ArrayList로 변환해서 리턴한다.
//    @GetMapping("/user/all")
//    public List<Userprofile> getUserProfileList(){
//        return new ArrayList<Userprofile>(userMap.values());
//    }
//
//    // @GetMapping은 데이터를 조회하는 api
//    // http의 프로토콜은 Get말고도 post, put, delete 방식이 있다.
//    // @PostMapping은 데이터를 수정할 때, @PutMapping은 데이터를 생성할 때, @DeleteMapping은 데이터를 삭제할 때 사용
//
//    @PutMapping("/user/{id}")  // 일반적인 브라우저로는 테스트하기 힘듦. Postman api로 테스트하는 것을 추천
//    public void putUserProfile(@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
//        Userprofile userprofile=new Userprofile(id, name, phone, address);
//        userMap.put(id, userprofile);
//    }
//
//    @PostMapping("/user/{id}")  // 수정하고 조회
//    public void postUserProfile(@RequestParam("id") String id,@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
//        Userprofile userprofile=userMap.get(id);
//        userprofile.setName(name);
//        userprofile.setPhone(phone);
//        userprofile.setAddress(address);
//    }
//
//    @DeleteMapping("/user/{id}")  // 삭제하는 api / id를 입력하면 해당 id 데이터 삭제됨
//    public void deleteUserProfile(@PathVariable("id") String id){
//        userMap.remove(id);
//    }
//}
