package com.store.controllers

import com.store.models.*
import com.store.services.ProductService
import com.store.utils.ProductUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import com.store.utils.ProductUtils.*
import com.store.utils.ProductUtils.Companion.isCostValid
import com.store.utils.ProductUtils.Companion.isInventoryValid
import com.store.utils.ProductUtils.Companion.isNameValid
import com.store.utils.ProductUtils.Companion.isTypeValid
import kotlin.reflect.typeOf


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
    fun addNewProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductInsertResponse> {
        if( isNameValid(productRequest.name) ||
            isTypeValid(productRequest.type) ||
            isInventoryValid(productRequest.inventory)||
            isCostValid(productRequest.cost)
        ){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProduct(productRequest))
    }

}
