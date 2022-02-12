package com.inux.forminhassolfi.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.adapter.ProdutoAdapter
import com.inux.forminhassolfi.api.MyRetrofit
import com.inux.forminhassolfi.classes.ActivityPadrao
import com.inux.forminhassolfi.interfacelistener.ProdutoClickedListener
import com.inux.forminhassolfi.model.Produto
import com.inux.forminhassolfi.util.ParametroSingleton
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ActivityPadrao() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        iniciarFormulario()

        imbQuemSomos.setOnClickListener{ abrirActivity ->
            startActivity(Intent(this, QuemSomos::class.java))
        }

        imbComoComprar.setOnClickListener{ abrirActivity ->
            startActivity(Intent(this, ComoComprar::class.java))
        }

        imbContato.setOnClickListener{ abrirActivity ->
            startActivity(Intent(this, Contato::class.java))
        }
    }

    override fun iniciarFormulario() {
        //Instanciando objetos XML.
        recyclerProduto = recycler_produto

        imbQuemSomos = imb_Quem_Somos
        imbComoComprar = imb_Como_Comprar
        imbContato = imb_Contato

        recyclerProduto.layoutManager = LinearLayoutManager(this)
        comporLista()
    }

    override fun comporLista() {
        val call: Call<List<Produto>> = MyRetrofit.instance?.produtoApi()?.getProdutoApi() as Call<List<Produto>>

        call.enqueue(object : Callback<List<Produto>> {
            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                val adapter = ProdutoAdapter(this@MainActivity, response.body()?.toList() as List<Produto>,
                    object : ProdutoClickedListener {
                        override fun produtoClickedListener(produto: Produto) {
                            startActivity(Intent(this@MainActivity, DetalhesProduto::class.java).apply {
                                putExtra(ParametroSingleton.INTENT_PRODUTO, produto)
                            })
                        }
                    })
                recyclerProduto.adapter = adapter
            }

            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun visualizarCarrinho() {
        Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_carrinho -> {
                visualizarCarrinho()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}