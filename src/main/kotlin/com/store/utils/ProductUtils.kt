package com.store.utils

import com.store.models.ProductType

class ProductUtils {

    companion object{
        fun isNameInvalid(name: String): Boolean{
            return name.toBooleanStrictOrNull() != null || name.toDoubleOrNull() != null
        }
        fun isTypeInvalid(type: ProductType): Boolean{
            return type == null
        }
        fun isInventoryInvalid(inventory: Int): Boolean{
            return inventory == 0
        }
        fun isCostInvalid(cost: Double): Boolean{
            return cost == 0.0
        }
    }

}