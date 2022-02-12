package com.inux.forminhassolfi.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "carrinho_table")
data class Carrinho (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val codigo: String,
    val produto: String,
    val cor: String,
    val quantidade: Int,
    val valor: Double
) : Parcelable