package com.cellep.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cellep.appestacaohack.databinding.ActivityCadastroBinding
import java.math.BigInteger
import java.security.MessageDigest

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
          return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //criar lista na nossa caixa de selecao
        val listaContinentes = arrayListOf("Continente", "Africa", "antartida", "America", "Asia", "Europa", "Oceania")

        //criando um adaptador para p Spinner
        val spinnerAdapter = ArrayAdapter(
            this, //contexto
        //    android.R.layout.simple_spinner_dropdown_item, //layout
            R.layout.layout_branco_item, //novo layout
        listaContinentes //lista de dados
        )
        // layout do menu Dropdown
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        //plugar o adaptador no splinner
        binding.spnCadastroContinente.adapter = spinnerAdapter
        // quando o botao Cadastrar for clicado faça
        binding.btnCadastroCadastrar.setOnClickListener{
            //recuperar os dados preenchidos pelo usuarios
            val nome = binding.edtCadastroNome.text.toString().trim()
            val sobrenome = binding.ediCadastroSobrenome.text.toString().trim()
            val email= binding.edtCadastroEmail.text.toString().trim().lowercase()
            val senha = binding.edtCadastroSenha.text.toString().trim()
            val continente = binding.spnCadastroContinente.selectedItem.toString()

            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || continente == listaContinentes[0] ){
                //se qualquer campo nao estiver preenchido com mensagem de alerta deve aparecer
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                //se todos os campos estiver preenchidos o escopo do else vai ser executado
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )
                //aqui tornamos o arquivo editavel
                val ediPrefs = sharedPrefs.edit()

                //aqui os dados sao preparados para serem salvo no arquivo
                // os dados sao salvos no formato chave -> valor
                ediPrefs.putString("NOME", nome)
                ediPrefs.putString("SOBRENOME", sobrenome)
                ediPrefs.putString("EMAIL", email)
                ediPrefs.putString("SENHA", Hash().md5(senha))
                ediPrefs.putString("CONTINENTE", continente)

                // aqui os dados sao salvos no arquivo
                ediPrefs.apply()

                Toast.makeText(this, "Cadsstro Concluido", Toast.LENGTH_LONG).show()

               //abrir a mainActivity apos clicar botao
                    val mIntent = Intent(this, MainActivity::class.java)

                //aqui é mostrado como passar dados entre activities(tela)
                mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                //esse metodo remove todas as activiys do empilhamento
                    finishAffinity()
            }
        }
    }
}