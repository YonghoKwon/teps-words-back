// com/tepswords/back/repository/ExpressionRepository.java
package com.tepswords.back.repository;

import com.tepswords.back.model.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpressionRepository extends JpaRepository<Expression, Integer> {
    List<Expression> findByCategoryCategoryId(Integer categoryId);
}