package com.inux.forminhassolfi.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.adapter.CarrinhoAdapter
import com.inux.forminhassolfi.classes.ActivityPadrao
import com.inux.forminhassolfi.database.entity.Carrinho
import com.inux.forminhassolfi.database.viewmodel.CarrinhoViewModel
import com.inux.forminhassolfi.interfacelistener.CarrinhoClickedListener
import com.inux.forminhassolfi.util.MetodosGlobais
import kotlinx.android.synthetic.main.tela_carrinho.*

class TelaCarrinho : ActivityPadrao() {
    private lateinit var mCarrinhoViewModel: CarrinhoViewModel
    private lateinit var globais: MetodosGlobais

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_carrinho)
        setSupportActionBar(findViewById(R.id.toolbar_Tela_Carrinho))

        // Instancias objetos.
        globais = MetodosGlobais(this)

        iniciarFormulario()

        btTelCarEnviarPedido.setOnClickListener {

        }
    }

    override fun iniciarFormulario() {
        // Iniciando view model.
        mCarrinhoViewModel = ViewModelProvider(this).get(CarrinhoViewModel::class.java)

        edtTelCarNome = edt_TelCar_Nome
        edtTelCarCelular = edt_TelCar_Celular
        edtTelCarCupom = edt_TelCar_Cupom
        recyclerCarrinho = recycler_carrinho_produto
        btTelCarEnviarPedido = bt_TelCar_EnviarPedido

        recyclerCarrinho.layoutManager = LinearLayoutManager(this)

        configuracao()
        comporLista()
    }

    override fun comporLista() {
        mCarrinhoViewModel.readAllData.observe(this, { carrinho ->
            if (carrinho != null) {
                if (carrinho.size > 0) {
                    val adapter = CarrinhoAdapter(this@TelaCarrinho, carrinho, object :
                        CarrinhoClickedListener {
                        override fun carrinhoClickedListener(carrinho: Carrinho) {
                            globais.mensagemSnack(
                                findViewById(android.R.id.content),
                                "Deletado item: ${carrinho.produto}",
                                resources
                            )
                        }
                    })
                    recyclerCarrinho.adapter = adapter
                } else {
                    globais.mensagemSnack(
                        findViewById(android.R.id.content),
                        "Carrinho Vazio.",
                        resources
                    )
                }
            } else {
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "Carrinho Vazio.",
                    resources
                )
            }
        })
    }

    private fun limparCarrinho() {
        globais.mensagemSnack(findViewById(android.R.id.content), "Limpando carrinho.", resources)
        //mCarrinhoViewModel.deleteAllCarrinho()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.carrinho_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_limpar -> {
                limparCarrinho()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}