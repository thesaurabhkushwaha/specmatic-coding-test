package com.store.controllers

import com.store.models.Product
import com.store.models.ProductInsertResponse
import com.store.models.ProductRequest
import com.store.models.ProductType
import com.store.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getProductsBasedOnType(@Valid @RequestParam("type") type:ProductType?): List<Product> {
        return productService.getAllProducts(type)
    }


    @PostMapping
    fun addNewProduct(@Valid @RequestBody(required = true) productRequest: ProductRequest): ResponseEntity<ProductInsertResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(productRequest))
    }

}
