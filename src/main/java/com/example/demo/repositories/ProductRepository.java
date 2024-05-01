package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :products ")
    List<Product> findProductsCategories(List<Product> products);


//    Esse método usa EntityGraph para fazer o join fetch das categorias dos produtos.
    @EntityGraph(attributePaths = {"categories"})
    Page<Product> findAll(Pageable pageable);

}
