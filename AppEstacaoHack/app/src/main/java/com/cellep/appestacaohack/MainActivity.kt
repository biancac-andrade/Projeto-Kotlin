package com.cellep.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cellep.appestacaohack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //aqui estamos recuperamos a informação por meio da Intent
        val email = intent.getStringExtra("INTENT_EMAIL")
        //Depuração
        //Toast.makeText(this, email, Toast.LENGTH_LONG).show()

        //Acessar o nosso arquivo de preferencias
        val sharedPrefs = getSharedPreferences(
            "cadastro_$email", Context.MODE_PRIVATE
        )

        //Recuperar os dados recuperados
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val continente = sharedPrefs.getString("CONTINENTE", "")

        binding.txvMainNome.text = "$nome $sobrenome"
        binding.txvMainEmail.text = email
        binding.txvMainContinente.text = continente

        //executano o clique do botao SAIR
        binding.btnMainSair.setOnClickListener{
            //criando uma caixa de dialogo
            var alert = AlertDialog.Builder(this)

            //definir o titulo da caixa de dialogo
            alert.setTitle("Atenção")

            //Definir a mensaegm do corpo da caixa de dialogo
            alert.setMessage("Deseja mesmo sair?")

            //definir o rotulo do botao saie e executar o clique
            alert.setPositiveButton("Sair"){
                dialog, which ->
                //navegar para a tela de login
                val mIntent = Intent(this, LoginActivity2::class.java)
                startActivity(mIntent)
                finishAffinity()
            }

            //definir o botao neutro
            alert.setNeutralButton("Não") {dialog, which ->}

            //desabilitar a possibilidade do usuario cancelar a caixa de dialogo ao clicar fora dela
            alert.setCancelable(false)

            //exibindo a caixa criada
            alert.show()
        }

            //botao site CELLEP
            binding.btnMainSite.setOnClickListener{
                //navegacao para o WebActivity
                val mIntent = Intent(this, WebActivity::class.java)
                startActivity(mIntent)
                //finishAffinity()
            }

    }
}