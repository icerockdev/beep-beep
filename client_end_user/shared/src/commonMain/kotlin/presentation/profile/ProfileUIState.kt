package presentation.profile

import domain.entity.Address
import domain.entity.Location
import domain.entity.User

data class ProfileUIState(
    val user: UserUIState? = UserUIState(),
    val fullName: String = "",
    val phoneNumber: String = "",
    val snackBarMessage: String = "",
    val showSnackBar: Boolean = false,
    val isFullNameError: Boolean = false,
    val isPhoneNumberError: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)

data class UserUIState(
    val addresses: List<AddressUIState>? = emptyList(),
    val email: String = "",
    val id: String = "",
    val username: String = "",
    val walletBalance: Double = 0.0,
    val fullName: String = "",
    val phoneNumber: String = "",
    val currency: String = ""
)

data class AddressUIState(
    val address: String = "",
    val id: String = "",
    val location: LocationUIState? = LocationUIState()
)

data class LocationUIState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

fun User.toUIState(): UserUIState {
    return UserUIState(
        addresses = addresses.map { it.toUIState() },
        email = email,
        id = id,
        username = username,
        walletBalance = wallet.value,
        fullName = fullName,
        phoneNumber = phoneNumber,
        currency = wallet.currency
    )
}

fun Address.toUIState(): AddressUIState {
    return AddressUIState(
        address = address ,
        id = id ,
        location = location?.toUIState()
    )
}

fun Location.toUIState(): LocationUIState {
    return LocationUIState(
        latitude = latitude ,
        longitude = longitude
    )
}
