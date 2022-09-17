package com.cellep.appestacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.cellep.appestacaohack.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //habilitar a execucao do codigo Javascript
        binding.wbvWeb.settings.javaScriptEnabled = true

        //definir a pagina inicial
        binding.wbvWeb.loadUrl("https://br.cellep.com/estacaohack")

        //definir o webview como navegador padrao da aplicacao
        binding.wbvWeb.webViewClient = WebViewClient()
    }
}