package com.anny.board.boardme.repository;

import com.anny.board.boardme.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
