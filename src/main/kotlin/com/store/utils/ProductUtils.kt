package com.store.utils

import com.store.models.ProductType

class ProductUtils {

    companion object{
        fun isNameValid(name: String): Boolean{
            return name.toBooleanStrictOrNull() != null || name.toDoubleOrNull() != null
        }
        fun isTypeValid(type: ProductType): Boolean{
            return type == null
        }
        fun isInventoryValid(inventory: Int): Boolean{
            return inventory == 0
        }
        fun isCostValid(cost: Int): Boolean{
            return cost == 0
        }
    }

}