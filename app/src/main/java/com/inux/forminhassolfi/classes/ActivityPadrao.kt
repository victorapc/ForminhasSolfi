package com.inux.forminhassolfi.classes

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

abstract class ActivityPadrao : AppCompatActivity() {
    protected lateinit var recyclerProduto: RecyclerView

    protected open fun configuracao(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    abstract fun iniciarFormulario()
    abstract fun comporLista()
}