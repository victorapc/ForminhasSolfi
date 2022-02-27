package com.inux.forminhassolfi.model

import com.google.gson.annotations.SerializedName

data class Cupom(
    @SerializedName("id")
    val id: Int,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("desconto")
    val desconto: Double
)