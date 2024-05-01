package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> find(PageRequest pageRequest) {
//        Aqui eu busco todas os produtos que foi especificado na paginação pelo size, porém por padrão é lazy loading
        Page<Product> list = repository.findAll(pageRequest);
//        Aqui eu faço um join fetch para trazer as categorias de cada produto de uma só vez evitando fazer varias consultas ao banco
        repository.findProductsCategories(list.stream().collect(Collectors.toList()));
        return list.map(ProductDTO::new);
    }
}
