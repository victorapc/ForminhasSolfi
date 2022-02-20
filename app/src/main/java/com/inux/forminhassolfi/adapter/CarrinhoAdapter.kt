package com.inux.forminhassolfi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.database.entity.Carrinho
import com.inux.forminhassolfi.interfacelistener.CarrinhoClickedListener
import kotlinx.android.synthetic.main.inflater_carrinho.view.*
import java.text.DecimalFormat

class CarrinhoAdapter(
    val context: Context,
    val carrinhoLista: List<Carrinho>,
    private val listener: CarrinhoClickedListener
) : RecyclerView.Adapter<CarrinhoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder =
        CarrinhoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflater_carrinho, parent, false)
        )

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val carrinho = carrinhoLista[position]

        val decimal = DecimalFormat("0.00")

        holder.inf_Car_Produto.text = "${carrinho.codigo} - ${carrinho.produto}"
        holder.inf_Car_Cor.text = carrinho.cor
        holder.inf_Car_Quantidade.text = Integer.toString(carrinho.quantidade)
        holder.inf_Car_Valor.text = "R$ ${decimal.format(carrinho.valor * carrinho.quantidade)}"

        holder.inf_bt_Car_Deletar.setOnClickListener {
            listener.carrinhoClickedListener(carrinho)
        }
    }

    override fun getItemCount(): Int = carrinhoLista.size
}

class CarrinhoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val inf_Car_Produto = itemView.inf_Car_Produto
    val inf_Car_Cor = itemView.inf_Car_Cor
    val inf_Car_Quantidade = itemView.inf_Car_Quantidade
    val inf_Car_Valor = itemView.inf_Car_Valor
    val inf_bt_Car_Deletar = itemView.inf_bt_Car_Deletar
}