package com.store.controllers

import com.store.models.Product
import com.store.models.ProductInsertResponse
import com.store.models.ProductRequest
import com.store.models.ProductType
import com.store.services.ProductService
import com.store.utils.ProductUtils.Companion.isCostInvalid
import com.store.utils.ProductUtils.Companion.isInventoryInvalid
import com.store.utils.ProductUtils.Companion.isNameInvalid
import com.store.utils.ProductUtils.Companion.isTypeInvalid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
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
    fun addNewProduct(@RequestBody(required = true) productRequest: ProductRequest): ResponseEntity<ProductInsertResponse> {
        if( isNameInvalid(productRequest.name) ||
            isTypeInvalid(productRequest.type) ||
            isInventoryInvalid(productRequest.inventory)||
            isCostInvalid(productRequest.cost)
        ){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(productRequest))
    }

}
