package uz.digital.dagger2.model.actual

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    val id: Int,
    val name: String,
    val phone: String
)