package com.inux.forminhassolfi.classes

import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

abstract class ActivityPadrao : AppCompatActivity() {
    //MainActivity.
    protected lateinit var recyclerProduto: RecyclerView
    protected lateinit var imbQuemSomos: ImageButton
    protected lateinit var imbComoComprar: ImageButton
    protected lateinit var imbContato: ImageButton

    protected open fun configuracao(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    abstract fun iniciarFormulario()
    abstract fun comporLista()
}