package com.inux.forminhassolfi.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.inux.forminhassolfi.database.data.SolphiDataBase
import com.inux.forminhassolfi.database.entity.Carrinho
import com.inux.forminhassolfi.database.repository.CarrinhoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarrinhoViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Carrinho>>
    private val repository: CarrinhoRepository

    init {
        val solphiDao = SolphiDataBase.getDataBase(application).sophiDao()
        repository = CarrinhoRepository(solphiDao)
        readAllData = repository.readAllData
    }

    fun addCarrinho(carrinho: Carrinho){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCarrinho(carrinho)
        }
    }

    fun deleteCarrinho(carrinho: Carrinho){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCarrinho(carrinho)
        }
    }

    fun deleteAllCarrinho(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCarrinho()
        }
    }

    fun readData(idTable: Int) : LiveData<Carrinho>{
        return repository.readData(idTable)
    }
}