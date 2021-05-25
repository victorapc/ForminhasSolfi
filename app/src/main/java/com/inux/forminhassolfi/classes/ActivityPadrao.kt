package com.inux.forminhassolfi.classes

import androidx.appcompat.app.AppCompatActivity

abstract class ActivityPadrao : AppCompatActivity() {
    protected open fun configuracao(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    abstract fun iniciarFormulario()
    abstract fun comporLista()
}