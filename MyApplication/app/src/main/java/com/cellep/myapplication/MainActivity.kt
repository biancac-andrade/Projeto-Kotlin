package com.cellep.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun jogarDado(view: View){
        var numero = Random.nextInt( 1,  7)
        var texto = findViewById(R.id.txvMainMessage) as TextView
        texto.text = "Numero: $numero"
    }
}