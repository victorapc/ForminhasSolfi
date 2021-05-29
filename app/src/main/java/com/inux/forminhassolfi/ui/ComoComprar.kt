package com.inux.forminhassolfi.ui

import android.os.Bundle
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.classes.ActivityPadrao

class ComoComprar : ActivityPadrao() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.como_comprar)
        setSupportActionBar(findViewById(R.id.toolbar_Como_Comprar))

        iniciarFormulario()
    }

    override fun iniciarFormulario() {
        configuracao()
    }

    override fun comporLista() {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}