package uz.digital.dagger2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.digital.dagger2.database.UserDao
import uz.digital.dagger2.database.UserDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideUserDatabase(
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "User.db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.dao
    }
}