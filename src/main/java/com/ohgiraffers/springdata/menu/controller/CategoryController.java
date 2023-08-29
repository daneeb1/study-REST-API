package com.ohgiraffers.springdata.menu.controller;



import com.ohgiraffers.springdata.menu.entity.Category;
import com.ohgiraffers.springdata.menu.dto.CategoryDTO;
import com.ohgiraffers.springdata.menu.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

  private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryCode}")
    public ResponseEntity<Object> findCategoryByCode(@PathVariable int categoryCode){
        Category category = categoryService.findCategoryByCode(categoryCode);

        if(Objects.isNull(category)){
            return ResponseEntity.status(404).body(new String("카테고리 코드가 존재하지 않습니다."));
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);

        return ResponseEntity.ok().body(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<?>> findAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();
        if(categoryList.size() <= 0){
            List<String> error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(m-> new CategoryDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTOList);
    }

    @PostMapping
    public ResponseEntity<?> registCategory(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        int result = categoryService.registCategory(category);

        return ResponseEntity.ok().body("카테고리 등록에 성공하였습니다.");
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(CategoryDTO categoryDTO){
        Category findCategory = categoryService.findCategoryByCode(categoryDTO.getCategoryCode());

        if (Objects.isNull(findCategory)){
            return ResponseEntity.ok().body("카테리가 존재하지 않습니다.");
        }

        int result = categoryService.updateCategory(findCategory, categoryDTO);

        if (result > 0){
            return ResponseEntity.ok().body("카테고리가 수정완료 되었습니다.");
        }else {
            return ResponseEntity.status(400).body("카테고리 수정에 실패하였습니다.");
        }
    }

    @DeleteMapping("{categoryCode}")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryCode){
        categoryService.deleteCategoryCode(categoryCode);

        return ResponseEntity.ok().body("카테고리 삭제에 성공하였습니다.");
    }

}
