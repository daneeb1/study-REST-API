package com.ohgiraffers.springdata.menu.service;

import com.ohgiraffers.springdata.entity.Category;
import com.ohgiraffers.springdata.menu.dto.CategoryDTO;
import com.ohgiraffers.springdata.menu.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryRepository categoryRespository;

    public CategoryService(CategoryRepository categoryRespository) {
        this.categoryRespository = categoryRespository;
    }

    public Category findCategoryByCode(int categoryCode) {

        Category category = categoryRespository.findById(categoryCode);
        return category;
    }

    public List<Category> findAllCategory() {

        List<Category> categoryList = categoryRespository.findAll();

        return categoryList;
    }
    @Transactional
    public int registCategory(Category category) {

        Category result = categoryRespository.save(category);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
    @Transactional
    public int updateCategory(Category findCategory, Category updateCategory) {

        if(!Objects.isNull(updateCategory.getCategoryCode())){
            findCategory.setCategoryName(updateCategory.getCategoryName());
        }

        Category result = categoryRespository.save(findCategory);

        if (Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
    @Transactional
    public void deleteCategoryCode(int delete) {
        categoryRespository.deleteById(delete);

        Category category = categoryRespository.findById(delete);
    }
}
