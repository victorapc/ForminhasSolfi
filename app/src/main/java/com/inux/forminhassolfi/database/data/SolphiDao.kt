package com.inux.forminhassolfi.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inux.forminhassolfi.database.entity.Carrinho

@Dao
interface SolphiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCarrinho(carrinho: Carrinho)

    @Delete
    suspend fun deleteCarrinho(carrinho: Carrinho)

    @Query("DELETE FROM carrinho_table")
    suspend fun deleteAllCarrinho()

    @Query("SELECT * FROM carrinho_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Carrinho>>

    @Query("SELECT * FROM carrinho_table WHERE(id = :idTable)")
    fun readData(idTable: Int): LiveData<Carrinho>
}