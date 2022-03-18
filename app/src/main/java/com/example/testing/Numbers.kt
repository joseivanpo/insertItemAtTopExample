package com.example.testing

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Numbers(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val number: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Numbers

        if (id != other.id) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + number
        return result
    }
}