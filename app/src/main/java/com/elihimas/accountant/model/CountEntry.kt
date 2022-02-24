package com.elihimas.accountant.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountEntry(
    @PrimaryKey var uid: Int? = null,
    val quantity: Int
) {
    constructor(quantity: Int) : this(null, quantity)
}
