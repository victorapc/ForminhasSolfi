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

        val decimal = DecimalFormat("#,###.00")

        holder.inf_Prod_Produto.text = item.produto
        holder.inf_Prod_Informacoes.text = item.informacoes
        holder.inf_Prod_Valor25.text = "R$ ${decimal.format(item.qnt25)}"
        holder.inf_Prod_Valor50.text = "R$ ${decimal.format(item.qnt50)}"
        holder.inf_Prod_Valor100.text = "R$ ${decimal.format(item.qnt100)}"
        holder.inf_Prod_Valor200.text = "R$ ${decimal.format(item.qnt200)}"
        holder.inf_Prod_Valor300.text = "R$ ${decimal.format(item.qnt300)}"
        if (item.qnt500 > 0){
            holder.inf_Prod_Valor500.text = "R$ ${decimal.format(item.qnt500)}"
        } else {
            holder.inf_Prod_Valor500.text = "R$ 0,00"
        }
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
    val inf_Prod_Valor25 = itemView.inf_Prod_Valor25
    val inf_Prod_Valor50 = itemView.inf_Prod_Valor50
    val inf_Prod_Valor100 = itemView.inf_Prod_Valor100
    val inf_Prod_Valor200 = itemView.inf_Prod_Valor200
    val inf_Prod_Valor300 = itemView.inf_Prod_Valor300
    val inf_Prod_Valor500 = itemView.inf_Prod_Valor500
}