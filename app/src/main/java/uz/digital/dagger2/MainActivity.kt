package uz.digital.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import uz.digital.dagger2.adapter.UserAdapter
import uz.digital.dagger2.databinding.ActivityMainBinding
import uz.digital.dagger2.model.actual.User
import uz.digital.dagger2.utils.UserState
import uz.digital.dagger2.viewmodel.MainEvent
import uz.digital.dagger2.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: UserViewModel
    private val userAdapter by lazy { UserAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.component.inject(this)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is UserState.Idle -> Unit
                    is UserState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is UserState.Error -> {
                        println("@@@${it.string}")
                        binding.progressBar.isVisible = false
                    }
                    is UserState.Success -> {
                        binding.progressBar.isVisible = false
                        userAdapter.submitList(it.userList)
                    }
                }
            }
        }
    }
}
