package com.inux.forminhassolfi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.model.Produto
import kotlinx.android.synthetic.main.inflater_produtos.view.*
import java.text.DecimalFormat

class ProdutoAdapter(
    val context: Context,
    val produtoLista: List<Produto>
) : RecyclerView.Adapter<ProdutoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder =
        ProdutoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.inflater_produtos, parent, false))

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val item = produtoLista[position]

        val decimal = DecimalFormat("0.00")

        holder.inf_Prod_Produto.text = "${item.codigo} - ${item.produto}"
        holder.inf_Prod_Informacoes.text = item.informacoes
        holder.inf_Prod_Valor.text = "R$ ${decimal.format(item.valor)}"
        Glide
            .with(context)
            .load(item.img)
            .into(holder.inf_Prod_imagem)
    }

    override fun getItemCount(): Int = produtoLista.size

}

class ProdutoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val inf_Prod_Produto = itemView.inf_Prod_Produto
    val inf_Prod_Informacoes = itemView.inf_Prod_Informacoes
    val inf_Prod_imagem = itemView.inf_Prod_imagem
    val inf_Prod_Valor = itemView.inf_Prod_Valor
}