package uz.digital.dagger2.di

import uz.digital.dagger2.MainActivity
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}