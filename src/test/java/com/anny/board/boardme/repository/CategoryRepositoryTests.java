package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.category.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 저장")
    void testSaveCategory() {
        Category category = Category.of()
                .name("programming gallery")
                .description("learn programming sharing each other")
                .build();

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory);
        assertTrue(savedCategory.getId() > 0);
    }
}
