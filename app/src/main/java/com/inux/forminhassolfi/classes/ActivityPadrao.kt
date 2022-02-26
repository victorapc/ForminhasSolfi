package com.inux.forminhassolfi.classes

import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

abstract class ActivityPadrao : AppCompatActivity() {
    // MainActivity.
    protected lateinit var recyclerProduto: RecyclerView
    protected lateinit var imbQuemSomos: ImageButton
    protected lateinit var imbComoComprar: ImageButton
    protected lateinit var imbContato: ImageButton
    protected lateinit var recylerProgressBarMain: ProgressBar

    // Contato.
    protected lateinit var imgFacebook: ImageView
    protected lateinit var imgWhatsApp: ImageView
    protected lateinit var imgInstagram: ImageView
    protected lateinit var imgEmail: ImageView

    // Detalhes produto.
    protected lateinit var imgDetProdImagem: ImageView
    protected lateinit var txtDetProdProduto: TextView
    protected lateinit var edtDetProdValor: TextInputEditText
    protected lateinit var edtDetProdQuantidae: TextInputEditText
    protected lateinit var edtDetProdCor: TextInputEditText
    protected lateinit var edtDetProdValorTotal: TextInputEditText
    protected lateinit var btDetProdIncluirCarrinho: Button

    // Tela carrinho.
    protected lateinit var edtTelCarNome: TextInputEditText
    protected lateinit var edtTelCarCelular: TextInputEditText
    protected lateinit var edtTelCarCupom: TextInputEditText
    protected lateinit var recyclerCarrinho: RecyclerView
    protected lateinit var btTelCarEnviarPedido: Button
    protected lateinit var recylerProgressBar: ProgressBar

    protected open fun configuracao(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    abstract fun iniciarFormulario()
    abstract fun comporLista()
}