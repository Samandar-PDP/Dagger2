package uz.digital.dagger2.mapper

import uz.digital.dagger2.model.UserDTOItem
import uz.digital.dagger2.model.actual.User

fun UserDTOItem.toUser(): User {
    return User(
        id = id,
        name = name,
        phone = phone
    )
}