package com.inux.forminhassolfi.classes

import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

abstract class ActivityPadrao : AppCompatActivity() {
    //MainActivity.
    protected lateinit var recyclerProduto: RecyclerView
    protected lateinit var imbQuemSomos: ImageButton
    protected lateinit var imbComoComprar: ImageButton
    protected lateinit var imbContato: ImageButton

    //Contato.
    protected lateinit var imgFacebook: ImageView
    protected lateinit var imgWhatsApp: ImageView
    protected lateinit var imgInstagram: ImageView
    protected lateinit var imgEmail: ImageView

    protected open fun configuracao(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    abstract fun iniciarFormulario()
    abstract fun comporLista()
}