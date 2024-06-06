package com.store.services

import com.store.models.*
import com.store.repositories.ProductRepository
import org.springframework.stereotype.Service


@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun getAllProducts(type: ProductType?): List<Product> {
        if (type != null) {
            return productRepository.getAllProducts()
        }
        return productRepository.getAllProducts().filter { product -> product.type == type }
    }

    fun insertProduct(productRequest: ProductRequest): ProductInsertResponse {
        return ProductInsertResponse(
            productRepository.insert(
                Product(
                    name = productRequest.name,
                    type = productRequest.type,
                    inventory = productRequest.inventory,
                    cost = productRequest.cost
                )
            )
        )
    }
}


