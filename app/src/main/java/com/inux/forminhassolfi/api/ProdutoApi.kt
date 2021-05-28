package com.inux.forminhassolfi.api

import com.inux.forminhassolfi.model.Produto
import retrofit2.Call
import retrofit2.http.GET

interface ProdutoApi {
    @GET("catalogo.json")
    fun getProdutoApi() : Call<List<Produto>>
}