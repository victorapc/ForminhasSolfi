package com.inux.forminhassolfi.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.inux.forminhassolfi.R
import com.inux.forminhassolfi.classes.ActivityPadrao
import kotlinx.android.synthetic.main.contato.*

class Contato : ActivityPadrao() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contato)
        setSupportActionBar(findViewById(R.id.toolbar_Contato))

        iniciarFormulario()

        imgFacebook.setOnClickListener{
            abrirWebLink("https://www.facebook.com/fsolfi")
        }

        imgWhatsApp.setOnClickListener{
            Toast.makeText(this, "Whatsapp", Toast.LENGTH_LONG).show()
        }

        imgInstagram.setOnClickListener{
            abrirWebLink("https://www.instagram.com/forminhas.solfi")
        }

        imgEmail.setOnClickListener{
            Toast.makeText(this, "E-mail", Toast.LENGTH_LONG).show()
        }
    }

    override fun iniciarFormulario() {
        imgFacebook = img_FaceBook
        imgWhatsApp = img_WhatsApp
        imgInstagram = img_Instagram
        imgEmail = img_Email

        configuracao()
    }

    override fun comporLista() {
        TODO("Not yet implemented")
    }

    private fun abrirWebLink(url: String){
        val intent: Intent = Uri.parse(url).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}