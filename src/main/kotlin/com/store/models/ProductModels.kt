package com.store.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class Product(
    var id: Int?=0,
    val name: String,
    val type: ProductType,
    val inventory: Int
)

data class ProductDTO(
    val id: Int,
    val name: String,
    val type: ProductType,
    val inventory: Int
)

data class ProductRequest(
    @field:NotNull
    @JsonProperty("name")
    val name: String,

    @field:NotNull
    @JsonProperty("type")
    val type: ProductType,

    @field:NotNull
    @JsonProperty("inventory")
    val inventory: Int
)

data class ProductResponse(
    @JsonProperty("id")
    val id: Int,
)

enum class ProductType(val type: String) {
    book("book"),
    food("food"),
    gadget("gadget"),
    other("other")
}
