package uz.digital.dagger2.viewmodel

import uz.digital.dagger2.model.actual.User

sealed class MainEvent {
    data class AddUser(val user: User): MainEvent()
    data class UpdateUser(val user: User) : MainEvent()
    data class DeleteUser(val id: Long): MainEvent()
    object GetUsers: MainEvent()
}