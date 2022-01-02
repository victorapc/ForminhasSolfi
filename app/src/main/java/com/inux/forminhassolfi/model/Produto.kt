package com.inux.forminhassolfi.model

import com.google.gson.annotations.SerializedName

data class Produto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("produto")
    val produto: String,
    @SerializedName("informacoes")
    val informacoes: String,
    @SerializedName("valor")
    val valor: Double,
    @SerializedName("img")
    val img: String
)