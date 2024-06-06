package com.store.controllers

import com.store.models.ProductDTO
import com.store.models.ProductRequest
import com.store.models.ProductResponse
import com.store.models.ProductType
import com.store.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid


@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/products")
    fun getProductsBasedOnType(@Valid @RequestParam("type") type:ProductType?): List<ProductDTO> {
        if(type != null && type !is ProductType)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        println(type !is ProductType)
        return productService.getAllProducts(type)
    }


    @PostMapping("/products")
    fun addNewProduct(@Valid @RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> {
        if( productRequest.name.toBooleanStrictOrNull() != null ||
            productRequest.name.isNullOrEmpty() ||
            productRequest.name.toDoubleOrNull() != null ||
            productRequest.type == null ||
            productRequest.inventory == 0)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(productRequest))
    }

}
