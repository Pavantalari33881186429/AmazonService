package com.aproduct.productdao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.aproduct.model.AProduct;




@Repository
public interface ProductRepository extends JpaRepository<AProduct, Long>{

}