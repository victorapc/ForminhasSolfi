package com.inux.forminhassolfi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.classes.ActivityPadrao
import com.inux.forminhassolfi.database.entity.Carrinho
import com.inux.forminhassolfi.database.viewmodel.CarrinhoViewModel
import com.inux.forminhassolfi.model.Produto
import com.inux.forminhassolfi.util.MetodosGlobais
import com.inux.forminhassolfi.util.ParametroSingleton.Companion.INTENT_PRODUTO
import kotlinx.android.synthetic.main.detalhes_produto.*

class DetalhesProduto : ActivityPadrao() {
    private lateinit var produto: Produto
    private lateinit var globais: MetodosGlobais
    private lateinit var mCarrinhoViewModel: CarrinhoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_produto)
        setSupportActionBar(findViewById(R.id.toolbar_Detalhes_Produto))

        // Instanciando objetos
        globais = MetodosGlobais(this)

        iniciarFormulario()

        btDetProdIncluirCarrinho.setOnClickListener {
            gravarCarrinho(passarCampos())
        }
    }

    override fun iniciarFormulario() {
        // Pegando dados da intent enviada via Parceable.
        produto = intent.extras?.getParcelable(INTENT_PRODUTO)!!

        // Instanciando o view model.
        mCarrinhoViewModel = ViewModelProvider(this).get(CarrinhoViewModel::class.java)

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
        // Cor.
        edtDetProdCor = edt_DetProd_Cor
        // Incluir carrinho.
        btDetProdIncluirCarrinho = bt_DetProd_IncluirCarrinho

        configuracao()
    }

    override fun comporLista() {

    }

    private fun passarCampos(): Boolean {
        var critica = false

        if (!critica) {
            if (edtDetProdQuantidae.text.toString()
                    .equals("") || edtDetProdQuantidae.text.toString().equals("0")
            ) {
                critica = true;
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "Obrigatório informar a quantidade.", resources
                )
            }
        }

        if (!critica) {
            if (edtDetProdCor.text.toString().equals("")) {
                critica = true;
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "Obrigatório informar a cor.", resources
                )
            }
        }

        return critica
    }

    private fun gravarCarrinho(critica: Boolean) {
        if (!critica) {
            var carrinho = Carrinho(
                0,
                produto.codigo,
                produto.produto,
                edtDetProdCor.text.toString(),
                Integer.parseInt(edtDetProdQuantidae.text.toString()),
                produto.valor
            )
            mCarrinhoViewModel.addCarrinho(carrinho)

            Toast.makeText(this, "Produto adicionado no carrinho com sucesso!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}