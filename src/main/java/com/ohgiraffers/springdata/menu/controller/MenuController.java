package com.ohgiraffers.springdata.menu.controller;

import com.ohgiraffers.springdata.menu.entity.Menu;
import com.ohgiraffers.springdata.menu.dto.MenuDTO;
import com.ohgiraffers.springdata.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 메뉴 코드로 조회
    @GetMapping("/{menuCode}")
    public ResponseEntity<Object> findMenuByCode(@PathVariable int menuCode){
        Menu menu = menuService.findMenuByCode(menuCode);

        if (Objects.isNull(menu)){
            return ResponseEntity.status(404).body(new String("다시 입력"));
        }
        MenuDTO menuDTO = new MenuDTO(menu);

        return ResponseEntity.ok().body(menuDTO);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<?>> findAllMenu(){
        List<Menu> menuList = menuService.findAllMenu();
        if(menuList.size() <= 0){
            List<String> error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok().body(menuList);
    }

    // 등록
    @PostMapping
    public ResponseEntity<?> regist(MenuDTO menuDTO){
        Menu menu = new Menu(menuDTO);
        int result = menuService.registMenu(menu);

        return ResponseEntity.ok().body("메뉴 등록에 성공하였습니다.");
    }

    // 수정
    @PutMapping
    public ResponseEntity<?> update(MenuDTO menuDTO){
        Menu findMenu = menuService.findMenuByCode(menuDTO.getMenuCode());
        // 메뉴 코드로 찾는다.

        if(Objects.isNull(findMenu)){
            return ResponseEntity.ok().body("메뉴가 존재하지 않습니다.");
        }

        int result = menuService.updateMenu(findMenu, menuDTO);

        if(result > 0){
            return ResponseEntity.ok().body("메뉴 수정이 완료되었습니다.");
        }else {
            return ResponseEntity.status(400).body("메뉴 수정에 실패하였습니다.");
        }
    }

    @DeleteMapping("{menuCode}")
    public ResponseEntity<?> delete(@PathVariable int menuCode){
        menuService.deleteCode(menuCode);

        return ResponseEntity.ok().body("메뉴 삭제에 성공하였습니다.");
    }
}
