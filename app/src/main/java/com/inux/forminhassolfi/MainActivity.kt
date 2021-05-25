package com.inux.forminhassolfi

import android.os.Bundle
import com.inux.forminhassolfi.classes.ActivityPadrao

class MainActivity : ActivityPadrao() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun iniciarFormulario() {
        TODO("Not yet implemented")
    }

    override fun comporLista() {
        TODO("Not yet implemented")
    }
}