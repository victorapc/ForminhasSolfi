package com.inux.forminhassolfi.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.math.BigDecimal
import java.text.*
import java.util.*

class MetodosGlobais(ctx: Context?) {
    private lateinit var dataObj: Date
    private val context = ctx

    fun formatarData(data: String) : String{
        val ano = data.substring(0, data.indexOf("-")).toInt()
        val mes = data.substring(data.indexOf("-") + 1, data.lastIndexOf("-")).toInt()
        val dia = data.substring(data.lastIndexOf("-") + 1).toInt()

        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dataFormatada: String = "$dia/$mes/$ano"

        try {
            dataObj = df.parse(dataFormatada)
        }catch (ex: ParseException){

        }

        val data = df.format(dataObj)
        return data
    }

    fun existeConexao(connectivity: ConnectivityManager) : Boolean {
        if (connectivity == null) {
            return false
        }
        val activeNetwork: NetworkInfo? = connectivity.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return isConnected
    }

    fun formataValor(valor: Double) : String {
        val df: DecimalFormat
        val vlr = BigDecimal(valor)

        df = DecimalFormat("#,##0.00")

        return df.format(vlr).replace(",", ".")
    }

    fun formataValor(valor: Double, tipo: Int) : String {
        val valorStrig: String
        val formatoReal: NumberFormat

        valorStrig = valor.toString()
        if(tipo == 0){
            formatoReal = NumberFormat.getCurrencyInstance(Locale("pt", "BR"));
        }else{
            formatoReal = NumberFormat.getCurrencyInstance(Locale("us", "US"));
        }

        val formatado = formatoReal.format(valorStrig.toDouble());

        return formatado;
    }
}