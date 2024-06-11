package com.store.repositories

import com.store.models.Product
import org.springframework.stereotype.Component

@Component
class ProductRepository {

    var productDB = ArrayList<Product>()
    var id = 1;
    fun getAllProducts(): List<Product> {
        return productDB
    }

    fun insert(product: Product): Int {
        product.id = id++;
        productDB.add(product)
        return id;
    }
}