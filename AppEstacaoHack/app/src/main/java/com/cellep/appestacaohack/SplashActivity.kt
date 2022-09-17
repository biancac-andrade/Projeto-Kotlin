package com.cellep.appestacaohack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //executando intrucoes apos determinado tempo que e 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
         //Intrucoes a serem executada apos o delay de 3 segundos

        //o metodo Intent permite nos navegarmos de uma activity a outra
        val mIntent = Intent(this, LoginActivity2::class.java)

        //esse e o metodo responsavel por executar a Intent
        startActivity(mIntent)

       //remover a tela(activity) da pilha
        finish()

        }, 3000)
    }
}