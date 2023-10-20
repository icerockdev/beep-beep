package org.thechance.api_gateway.data.model.offer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String,
)
