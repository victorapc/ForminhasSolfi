package com.inux.forminhassolfi.database.repository

import androidx.lifecycle.LiveData
import com.inux.forminhassolfi.database.data.SolphiDao
import com.inux.forminhassolfi.database.entity.Carrinho

class CarrinhoRepository(private val solphiDao: SolphiDao) {
    val readAllData: LiveData<List<Carrinho>> = solphiDao.readAllData()

    suspend fun addCarrinho(carrinho: Carrinho) {
        solphiDao.addCarrinho(carrinho)
    }

    suspend fun deleteCarrinho(carrinho: Carrinho) {
        solphiDao.deleteCarrinho(carrinho)
    }

    suspend fun deleteAllCarrinho() {
        solphiDao.deleteAllCarrinho()
    }

    fun readData(idTable: String): LiveData<Carrinho> {
        return solphiDao.readData(idTable)
    }
}