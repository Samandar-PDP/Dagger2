package uz.digital.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import uz.digital.dagger2.repository.UserRepository
import uz.digital.dagger2.utils.UserState
import uz.digital.dagger2.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.component.inject(this)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is UserState.Idle -> Unit
                    is UserState.Loading -> {
                        // progressbar show
                    }
                    is UserState.Error -> {
                        println("@@@${it.string}")
                    }
                    is UserState.Success -> {
                        println("@@@${it.userList}")
                    }
                }
            }
        }
    }
}
