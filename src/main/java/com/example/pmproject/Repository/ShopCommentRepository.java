package com.example.pmproject.Repository;

import com.example.pmproject.Entity.ShopComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCommentRepository extends JpaRepository<ShopComment, Long> {

    @Query("select u From ShopComment u Where u.shop.shopId = :shopId")
    List<ShopComment> findByShopId(@Param("shopId") Long shopId);

}
