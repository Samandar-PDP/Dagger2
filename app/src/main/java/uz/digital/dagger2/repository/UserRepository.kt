package uz.digital.dagger2.repository

import kotlinx.coroutines.flow.flow
import uz.digital.dagger2.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRemoteUsers() = flow { emit(apiService.getUsers()) }
}