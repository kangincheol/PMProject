package com.example.pmproject.Repository;

import com.example.pmproject.Entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    @Query("select u From ProductComment u Where u.product.productId = :productId")
    List<ProductComment> findByProductId(@Param("productId") Long productId);


}
