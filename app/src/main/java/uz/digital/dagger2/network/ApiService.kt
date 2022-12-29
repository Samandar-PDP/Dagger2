package uz.digital.dagger2.network

import retrofit2.Response
import retrofit2.http.GET
import uz.digital.dagger2.model.UserDTO

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<UserDTO>
}