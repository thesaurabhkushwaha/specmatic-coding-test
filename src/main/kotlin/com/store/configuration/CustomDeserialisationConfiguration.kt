package com.store.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.store.models.ProductRequest
import com.store.models.ProductType
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import com.store.utils.ProductUtils.Companion.validateTypeOfProduct

class MyRequestDeserializer : JsonDeserializer<ProductRequest>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ProductRequest {
        val mapper = p.codec as ObjectMapper
        val node = mapper.readTree(p) as JsonNode
        val name = node.get("name")
        val type = node.get("type")
        val inventory = node.get("inventory")
        val cost = node.get("cost")

        validateTypeOfProduct(type)
        if (name.isTextual && inventory.isIntegralNumber && cost.isNumber) {
            return ProductRequest(name.asText(), ProductType.valueOf(type.asText()), inventory.asInt(), cost.asDouble())
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}

@Configuration
open class MyConfiguration {
    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            val module = SimpleModule()
            module.addDeserializer(ProductRequest::class.java, MyRequestDeserializer())
            builder.modules(module)
        }
    }
}