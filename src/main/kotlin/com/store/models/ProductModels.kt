package com.store.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class Product(
    var id: Int?=0,
    val name: String,
    val type: ProductType,
    val inventory: Int,
    val cost: Double
)

data class ProductRequest(
    @field:NotNull
    @JsonProperty("name")
    val name: String,

    @field:NotNull(message = "type cannot be null")
    @JsonProperty("type")
    val type: ProductType,

    @field:NotNull(message = "inventory cannot be null")
    @field:Min(value = 1)
    @JsonProperty("inventory")
    val inventory: Int,

    @field:NotNull(message = "cost cannot be null")
    @JsonProperty("cost")
    val cost: Double
)

data class ProductInsertResponse(
    @JsonProperty("id")
    val id: Int,
)

enum class ProductType(val type: String) {
    book("book"),
    food("food"),
    gadget("gadget"),
    other("other")
}
