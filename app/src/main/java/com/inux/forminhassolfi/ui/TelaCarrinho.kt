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
import com.inux.forminhassolfi.model.Cupom
import com.inux.forminhassolfi.util.MetodosGlobais
import com.inux.forminhassolfi.util.ParametroSingleton
import kotlinx.android.synthetic.main.tela_carrinho.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class TelaCarrinho : ActivityPadrao() {
    private lateinit var mCarrinhoViewModel: CarrinhoViewModel
    private lateinit var globais: MetodosGlobais
    private lateinit var adapter: CarrinhoAdapter
    private var listaCarrinhoGlobal: List<Carrinho>? = null
    private var descontoCupom: Int? = 0
    private var cupom: Cupom? = null

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

        btTelCarAplicarCupom.setOnClickListener {
            cupom = Cupom(1, "OTAV", 5.0)
            calcularTotal()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun iniciarFormulario() {
        descontoCupom = 0
        // Iniciando view model.
        mCarrinhoViewModel = ViewModelProvider(this).get(CarrinhoViewModel::class.java)

        edtTelCarNome = edt_TelCar_Nome
        edtTelCarCelular = edt_TelCar_Celular
        edtTelCarCupom = edt_TelCar_Cupom
        recyclerCarrinho = recycler_carrinho_produto
        btTelCarEnviarPedido = bt_TelCar_EnviarPedido
        btTelCarAplicarCupom = bt_TelCar_AplicarCupom

        txtTelCarTotalBruto = txt_TelCar_TotalBruto
        txtTelCarCupom = txt_TelCar_Cupom
        txtTelCarTotalLiquido = txt_TelCar_TotalLiquido

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
                            listaCarrinhoGlobal = listaCarrinho

                            calcularTotal()

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

    private fun aplicouDesconto() : Boolean {
        var isDesconto = false

        if (!edtTelCarCupom.text.isNullOrEmpty()) {
            if (cupom != null) {
                if (edtTelCarCupom.text.toString().uppercase() == cupom!!.codigo) {
                    isDesconto = true
                }
            }
        }

        return isDesconto
    }


    private fun calcularTotal() {
        var totalBruto = 0.00
        var descCupom = 0.00
        var totalLiquido = 0.00

        listaCarrinhoGlobal?.forEach { carrinho ->
            totalBruto = totalBruto + (carrinho.valor * carrinho.quantidade)
        }

        txtTelCarTotalBruto.text = "R$ ${globais.formataValor(totalBruto)}"
        if (aplicouDesconto()) {
            if (cupom != null) {
                descCupom = totalBruto * (cupom!!.desconto / 100)
            }
        }
        txtTelCarCupom.text = "R$ ${globais.formataValor(descCupom)}"
        totalLiquido = totalBruto - descCupom
        txtTelCarTotalLiquido.text = "R$ ${globais.formataValor(totalLiquido)}"
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
        var critica = false

        if (!critica) {
            if (listaCarrinhoGlobal == null) {
                critica = true
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "Não é possível enviar pedido sem produtos.",
                    resources
                )
            }
        }

        if (!critica) {
            if (edtTelCarNome.text.isNullOrEmpty()) {
                critica = true
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "É obrigatório informar o nome.",
                    resources
                )
            }
        }

        if (!critica) {
            if (edtTelCarCelular.text.isNullOrEmpty()) {
                critica = true
                globais.mensagemSnack(
                    findViewById(android.R.id.content),
                    "É obrigatório informar o telefone.",
                    resources
                )
            }
        }

        if (!critica) {
            var pedido = StringBuilder()
            pedido.append("NOME: ")
            pedido.append(edtTelCarNome.text.toString())
            pedido.append("\n")
            pedido.append("CELULAR: ")
            pedido.append(edtTelCarCelular.text.toString())
            pedido.append("\n")
            pedido.append("CUPOM: ")
            pedido.append(edtTelCarCupom.text.toString())
            pedido.append("\n")
            pedido.append("       --------------------")
            pedido.append("\n")
            listaCarrinhoGlobal?.forEach { carrinho ->
                pedido.append(carrinho.quantidade)
                pedido.append(" | ")
                pedido.append(carrinho.codigo)
                pedido.append(" - ")
                pedido.append(carrinho.produto)
                pedido.append(" | R$ ")
                pedido.append(carrinho.quantidade * carrinho.valor)
                pedido.append("\n")
            }
            pedido.append("       --------------------")
            pedido.append("\n")
            pedido.append("TOTAL BRUTO: ")
            pedido.append(txtTelCarTotalBruto.text.toString())
            pedido.append("\n")
            pedido.append("DESC. CUPOM: ")
            pedido.append(txtTelCarCupom.text.toString())
            pedido.append("\n")
            pedido.append("TOTAL FINAL: ")
            pedido.append(txtTelCarTotalLiquido.text.toString())
            pedido.append("\n")

            val intent = Intent(Intent.ACTION_VIEW).apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, pedido.toString())
                setPackage("com.whatsapp")
                type = "text/plain"
            }
            startActivity(intent)

            globais.mensagemSnack(
                findViewById(android.R.id.content),
                "Pedido Enviado.",
                resources
            )
        }
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