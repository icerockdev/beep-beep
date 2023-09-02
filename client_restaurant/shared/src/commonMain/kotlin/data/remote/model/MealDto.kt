package data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    @SerialName("id")
    val id: String,
    @SerialName("restaurantId")
    val restaurantId: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("price")
    val price: Double? = null,
    @SerialName("cuisines")
    val cuisines: List<CuisineDto>? = null
)


