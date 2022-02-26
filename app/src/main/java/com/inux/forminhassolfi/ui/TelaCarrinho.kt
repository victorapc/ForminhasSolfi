package com.inux.forminhassolfi.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaCarrinho : ActivityPadrao() {
    private lateinit var mCarrinhoViewModel: CarrinhoViewModel
    private lateinit var globais: MetodosGlobais
    private lateinit var adapter: CarrinhoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_carrinho)
        setSupportActionBar(findViewById(R.id.toolbar_Tela_Carrinho))

        // Instancias objetos.
        globais = MetodosGlobais(this)

        iniciarFormulario()

        btTelCarEnviarPedido.setOnClickListener {
            enviarPedido()
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

        recylerProgressBar = recyler_ProgressBar

        recyclerCarrinho.layoutManager = LinearLayoutManager(this)

        configuracao()
        comporLista()
    }

    override fun comporLista() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                recylerProgressBar.visibility = View.VISIBLE

                mCarrinhoViewModel.readAllData.observe(this@TelaCarrinho, { listaCarrinho ->
                    if (listaCarrinho != null) {
                        adapter = CarrinhoAdapter(this@TelaCarrinho, listaCarrinho, object :
                            CarrinhoClickedListener {
                            override fun carrinhoClickedListener(carrinho: Carrinho) {
                                excluirRegistro("Deseja excluir o item do carrinho?", 1, carrinho)
                            }
                        })
                        recyclerCarrinho.adapter = adapter

                        if (listaCarrinho.size == 0) {
                            recylerProgressBar.visibility = View.GONE

                            globais.mensagemSnack(
                                findViewById(android.R.id.content),
                                "Carrinho Vazio.",
                                resources
                            )
                        } else {
                            recylerProgressBar.visibility = View.GONE
                        }
                    } else {
                        recylerProgressBar.visibility = View.GONE

                        globais.mensagemSnack(
                            findViewById(android.R.id.content),
                            "Carrinho Vazio.",
                            resources
                        )
                    }
                })
            } catch (e: Exception) {
                recylerProgressBar.visibility = View.GONE
            }
        }
    }

    private fun excluirRegistro(mensagem: String, tipo: Int, carrinho: Carrinho?) {
        val builder = AlertDialog.Builder(this@TelaCarrinho)
        builder.setPositiveButton("Sim"){ _,_ ->
            if (tipo == 0) {
                mCarrinhoViewModel.deleteAllCarrinho()
                finish()
            } else {
                mCarrinhoViewModel.deleteCarrinho(carrinho!!)

                adapter.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Não"){ _,_ ->}
        builder.setTitle(resources.getString(R.string.app_name))
        builder.setMessage(mensagem)
        builder.create().show()
    }

    private fun enviarPedido() {
        globais.mensagemSnack(
            findViewById(android.R.id.content),
            "Pedido Enviado.",
            resources
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.carrinho_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_limpar -> {
                excluirRegistro("Deseja limpar o carrinho?", 0, null)
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