package com.cellep.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cellep.appestacaohack.databinding.ActivityLogin2Binding
import android.widget.Toast.makeText as makeText1

class LoginActivity2 : AppCompatActivity() {
    //tecnica utilizada para inicializar uma variavel somente com sua classe
    private lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
 // quando o botao for clicado, faca
        binding.btnLoginEntrar.setOnClickListener{
            // aqui iremos capturar dos dados digitados pelo usuario
            val email = binding.edtLoginEmail.text.toString().trim().lowercase()
            val senha = binding.edtLoginSenha.text.toString().trim().lowercase()

            //valdacao dos campos
            if(email.isEmpty()){
                binding.edtLoginEmail.error = "Campo Obrigatorio"
                binding.edtLoginEmail.requestFocus()

            }else if(senha.isEmpty()){
                binding.edtLoginSenha.error = "Campo Obrigatorio"
                binding.edtLoginSenha.requestFocus()

            }else{
                //acessando o arquivo de preferencias compartilhadas
                 val sharedPrefs  = getSharedPreferences(
                     "cadastro_$email",
                     Context.MODE_PRIVATE
                 )
                // Recuperar os dados no arquivo
                val emailPrefs = sharedPrefs.getString("EMAIL","")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //aqui sera validado se o email e senha estao corretos
                if(email == emailPrefs && Hash().md5(senha) == senhaPrefs){
                   //se tiver tudo certo, apresentar uma mensagem para o usuario
                    Toast.makeText(this, "Usuario Logado", Toast.LENGTH_LONG).show()

                    //em seguida, navegar para tela do MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)
                    //passando o email via Intent para a MainActivity
                    mIntent.putExtra("INTENT_EMAIL", email)
                       // faz a navegaacao da nossa intent
                    startActivity(mIntent)
                    //emtao eliminamos a activity na pilha
                    finish()
                }else{
                    //se o email ou senha nao forem corretas, mostrar uma mensagem de erro
                    Toast.makeText(this, " Email ou senha invalida", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Botao Cadastrar
        binding.btnLoginCadastrar.setOnClickListener{
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
            finish()
        }

    }
}