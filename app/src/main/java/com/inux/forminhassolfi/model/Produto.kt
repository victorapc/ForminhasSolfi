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
    @SerializedName("qnt25")
    val qnt25: Double,
    @SerializedName("qnt50")
    val qnt50: Double,
    @SerializedName("qnt100")
    val qnt100: Double,
    @SerializedName("qnt200")
    val qnt200: Double,
    @SerializedName("qnt300")
    val qnt300: Double,
    @SerializedName("qnt500")
    val qnt500: Double,
    @SerializedName("img")
    val img: String
)