package com.store.utils

import com.fasterxml.jackson.databind.JsonNode
import com.store.models.ProductType
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ProductUtils {

    companion object{
        fun validateTypeOfProduct(type: JsonNode) {
            try {
                ProductType.valueOf(type.asText())
            }catch (e: Exception){
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)
            }
        }
    }
}