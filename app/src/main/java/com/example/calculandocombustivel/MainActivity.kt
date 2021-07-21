package com.example.calculandocombustivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException


lateinit var distancia: EditText
lateinit var preco: EditText
lateinit var autonomia: EditText
lateinit var buttonCalcular: Button
lateinit var valorTotalText: TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carregarElementos()

        buttonCalcular.setOnClickListener(this)

      
    }

    private fun carregarElementos() {
        distancia = findViewById(R.id.distancia)
        preco = findViewById(R.id.edit_preco)
        autonomia = findViewById(R.id.autonomia)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        valorTotalText = findViewById(R.id.text_valorTotal)
    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == R.id.buttonCalcular) {
            calculate()
        }
    }

    private fun calculate() {
        if(validationOk()) {

            try {
                val distance = distancia.text.toString().toFloat()
                val price = preco.text.toString().toFloat()
                val autonomy = autonomia.text.toString().toFloat()

                val totalValor = distance * price / autonomy
                valorTotalText.text = "R$ ${getString(R.string.formatação_de_números_flutuantes).format(totalValor)}"
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Informe números válidos", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, getString(R.string.preencher_todos_os_Campos), Toast.LENGTH_LONG).show()
        }
    }

    private fun validationOk(): Boolean = (distancia.text.toString() != ""
            && preco.text.toString() != ""
            && autonomia.text.toString() != ""
            && autonomia.text.toString() != "0")


}