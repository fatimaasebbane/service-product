package com.microservices.product_service.services;

import com.microservices.product_service.DTO.ProductRequest;
import com.microservices.product_service.DTO.ProductResponse;
import com.microservices.product_service.domains.models.Product;
import com.microservices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

 private final ProductRepository productRepository;
 // @RequiredArgsConstructor va creer un constructeur et initialiser cette variable lors de la compilation


 public  void createProduct(ProductRequest productRequest) {
  Product product = Product.builder()
          .name(productRequest.getName())
          .description(productRequest.getDescription())
          .price(productRequest.getPrice())
          .build();
  productRepository.save(product);
  log.info("Product {} is saved",product.getId());
 }


 public List<ProductResponse> getAllProducts() {
  List<Product> products=productRepository.findAll();
  return  products.stream().map(this::mapToProductResponse).toList();
 }

 private ProductResponse mapToProductResponse(Product product){
  return  ProductResponse.builder()
          .id(product.getId())
          .name(product.getName())
          .description(product.getDescription())
          .price(product.getPrice())
          .build();
 }

}
