package com.store.services

import com.store.models.*
import com.store.repositories.ProductRepository
import org.springframework.stereotype.Service


@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun getAllProducts(type: ProductType?): List<ProductDTO> {
        if (type != null) {
            return productRepository.getAllProducts().map { product ->
                ProductDTO(product.id!!, product.name, product.type, product.inventory)
            }
        }
        return productRepository.getAllProducts().filter { product -> product.type == type }
            .map { product ->
                ProductDTO(product.id!!, product.name, product.type, product.inventory)
            }
    }

    fun insertProduct(productRequest: ProductRequest): ProductResponse {
        return ProductResponse(
            productRepository.insert(
                Product(
                    name = productRequest.name,
                    type = productRequest.type,
                    inventory = productRequest.inventory
                )
            )
        )
    }
}


