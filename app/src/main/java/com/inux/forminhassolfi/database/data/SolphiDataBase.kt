package com.inux.forminhassolfi.database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inux.forminhassolfi.database.entity.Carrinho

@Database(entities = [Carrinho::class], version = 1, exportSchema = false)
abstract class SolphiDataBase: RoomDatabase() {
    abstract fun sophiDao(): SolphiDao

    companion object {
        @Volatile
        private var INSTANCE: SolphiDataBase? = null

        fun getDataBase(context: Context): SolphiDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SolphiDataBase::class.java,
                    "solphi_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}