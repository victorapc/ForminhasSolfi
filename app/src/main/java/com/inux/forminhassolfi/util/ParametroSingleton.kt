package com.inux.forminhassolfi.util

import com.inux.forminhassolfi.model.Produto

class ParametroSingleton {
    companion object {
        lateinit var produto: Produto
    }
}