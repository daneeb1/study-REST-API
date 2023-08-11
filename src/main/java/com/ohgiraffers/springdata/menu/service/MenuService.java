package com.ohgiraffers.springdata.menu.service;

import com.ohgiraffers.springdata.entity.Menu;
import com.ohgiraffers.springdata.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findMenuByCode(int menuCode) {
        Menu menu = menuRepository.findById(menuCode);
        return menu;
    }

    public List<Menu> findAllMenu() {

        List<Menu> menuList = menuRepository.findAll();
        return menuList;
    }

    @Transactional
    public int registMenu(Menu menu) {

        Menu result = menuRepository.save(menu);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }

    @Transactional
    public int updateMenu(Menu findMenu, Menu updateMenu) {

        if(!Objects.isNull(updateMenu.getMenuName())){
            findMenu.setMenuName(updateMenu.getMenuName());
            // 메뉴 이름만 수정
        }
        Menu result = menuRepository.save(findMenu);

        if (Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }

    @Transactional
    public void deleteCode(int delete) {

        menuRepository.deleteById(delete);

        Menu menu = menuRepository.findById(delete);

    }
}
