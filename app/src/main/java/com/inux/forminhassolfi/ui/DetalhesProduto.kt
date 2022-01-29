package com.inux.forminhassolfi.ui

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.classes.ActivityPadrao
import com.inux.forminhassolfi.model.Produto
import com.inux.forminhassolfi.util.MetodosGlobais
import com.inux.forminhassolfi.util.ParametroSingleton.Companion.INTENT_PRODUTO
import kotlinx.android.synthetic.main.detalhes_produto.*

class DetalhesProduto : ActivityPadrao() {
    private lateinit var produto: Produto
    private lateinit var globais: MetodosGlobais

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_produto)
        setSupportActionBar(findViewById(R.id.toolbar_Detalhes_Produto))

        // Instanciando objetos
        globais = MetodosGlobais(this)

        iniciarFormulario()

        btDetProdIncluirCarrinho.setOnClickListener{
            Toast.makeText(this, "Incluiu", Toast.LENGTH_LONG).show()
        }
    }

    override fun iniciarFormulario() {
        // Pegando dados da intent enviada via Parceable.
        produto = intent.extras?.getParcelable(INTENT_PRODUTO)!!

        // Imagem.
        imgDetProdImagem = img_DetProd_Imagem

        Glide
            .with(this)
            .load(produto.img)
            .into(img_DetProd_Imagem)
        // Produto.
        txtDetProdProduto = txt_DetProd_Produto
        txtDetProdProduto.text = produto.codigo + " - " + produto.produto
        // Valor.
        edtDetProdValor = edt_DetProd_Valor
        edtDetProdValor.setText("R$ ${globais.formataValor(produto.valor)}")
        edtDetProdValor.isEnabled = false
        // Quantidade.
        edtDetProdQuantidae = edt_DetProd_Quantidade
        edtDetProdQuantidae.setText("1")
        // Incluir carrinho.
        btDetProdIncluirCarrinho = bt_DetProd_IncluirCarrinho

        configuracao()
    }

    override fun comporLista() {

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}