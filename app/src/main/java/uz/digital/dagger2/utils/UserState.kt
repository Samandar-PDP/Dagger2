package uz.digital.dagger2.utils

import uz.digital.dagger2.model.actual.User

sealed class UserState  {
    object Idle: UserState()
    object Loading: UserState()
    data class Error(val string: String): UserState()
    data class Success(val userList: List<User>): UserState()
}