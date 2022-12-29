package uz.digital.dagger2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.digital.dagger2.mapper.toUser
import uz.digital.dagger2.repository.UserRepository
import uz.digital.dagger2.utils.UserState
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _state: MutableStateFlow<UserState> = MutableStateFlow(UserState.Idle)
    val state: StateFlow<UserState> get() = _state

    init {
        getRemoteUser()
    }

    private fun getRemoteUser() {
        viewModelScope.launch {
            _state.value = UserState.Loading
            delay(500L)
            try {
                repository.getRemoteUsers().collect {
                    if (it.isSuccessful) {
                        _state.value = UserState.Success(it.body()?.map { item ->
                            item.toUser()
                        }!!)
                    }
                }
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message.toString())
            }
        }
    }
}