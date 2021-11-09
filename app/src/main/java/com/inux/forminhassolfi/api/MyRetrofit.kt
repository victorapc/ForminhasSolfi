package com.inux.forminhassolfi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit {
    private val retrofit: Retrofit

    companion object {
        private const val BASE_URL =
            "http://forminhassophi.000webhostapp.com/"
        var myRetrofit: MyRetrofit? = null

        @get:Synchronized
        val instance: MyRetrofit?
            get(){
                if(myRetrofit == null){
                    myRetrofit = MyRetrofit()
                }

                return myRetrofit
            }
    }

    fun produtoApi() : ProdutoApi {
        return retrofit.create(ProdutoApi::class.java)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}