package com.inux.forminhassolfi.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
            abrirWhatsApp()
        }

        imgInstagram.setOnClickListener{
            abrirWebLink("https://www.instagram.com/forminhas.solfi")
        }

        imgEmail.setOnClickListener{
            abrirEmail()
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

    private fun abrirEmail(){
        startActivity(Intent(Intent.ACTION_SEND).apply {
            // The intent does not have a URI, so declare the "text/plain" MIME type
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email))) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Orçamento de Forminhas")
            putExtra(Intent.EXTRA_TEXT, "")
            //putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
            // You can also attach multiple items by passing an ArrayList of Uris
        })
    }

    private fun abrirWhatsApp(){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://api.whatsapp.com/send?phone=33999863240}")
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}