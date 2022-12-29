package uz.digital.dagger2

import android.app.Application
import uz.digital.dagger2.di.AppComponent
import uz.digital.dagger2.di.DaggerAppComponent

class MyApp : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .build()
    }
}