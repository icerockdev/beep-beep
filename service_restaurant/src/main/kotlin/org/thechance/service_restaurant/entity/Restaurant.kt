package org.thechance.service_restaurant.entity

data class Restaurant(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val priceLevel: String? = null,
    val rate: Double? = null,
    val phone: String? = null,
    val openingTime: String? = null,
    val closingTime: String? = null,
    val isDeleted: Boolean = false
//    val address: ObjectId,
)

