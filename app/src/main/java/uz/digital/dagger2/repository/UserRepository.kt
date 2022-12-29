package uz.digital.dagger2.repository

import kotlinx.coroutines.flow.flow
import uz.digital.dagger2.database.UserDao
import uz.digital.dagger2.model.actual.User
import uz.digital.dagger2.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: UserDao
) {
    suspend fun getRemoteUsers() = flow { emit(apiService.getUsers()) }

    suspend fun addUser(user: User) = dao.saveUser(user)
    suspend fun deleteUser(id: Long) = dao.deleteUser(id)
    suspend fun updateUser(user: User) = dao.updateUser(user)

    fun getLocalUsers() = dao.getAllUsers()
}