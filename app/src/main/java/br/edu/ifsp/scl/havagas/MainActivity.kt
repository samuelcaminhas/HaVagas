package br.edu.ifsp.scl.havagas

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addCelularCb = findViewById<CheckBox>(R.id.addCelularCb)
        val celularEt = findViewById<EditText>(R.id.celularEt)

        addCelularCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                celularEt.visibility = View.VISIBLE
            } else {
                celularEt.visibility = View.GONE
            }
        }

        val nascimentoEt = findViewById<EditText>(R.id.nascimentoEt)
        nascimentoEt.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(
                    this,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        nascimentoEt.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                    },
                    year,
                    month,
                    day
                )
            datePickerDialog.show()
        }
        val formacaoSp = findViewById<Spinner>(R.id.formacaoSp)
        val anoFormaturaEt = findViewById<EditText>(R.id.anoFormaturaEt)
        val instituicaoEt = findViewById<EditText>(R.id.instituicaoEt)
        val tituloMonografiaEt = findViewById<EditText>(R.id.tituloMonografiaEt)
        val orientadorEt = findViewById<EditText>(R.id.orientadorEt)

        formacaoSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    when (parent.getItemAtPosition(position).toString()) {
                        "Fundamental",
                        "Médio" -> {
                            anoFormaturaEt.visibility = View.VISIBLE
                            instituicaoEt.visibility = View.GONE
                            tituloMonografiaEt.visibility = View.GONE
                            orientadorEt.visibility = View.GONE
                        }
                        "Graduação",
                        "Especialização" -> {
                            anoFormaturaEt.visibility = View.VISIBLE
                            instituicaoEt.visibility = View.VISIBLE
                            tituloMonografiaEt.visibility = View.GONE
                            orientadorEt.visibility = View.GONE
                        }
                        "Mestrado",
                        "Doutorado" -> {
                            anoFormaturaEt.visibility = View.VISIBLE
                            instituicaoEt.visibility = View.VISIBLE
                            tituloMonografiaEt.visibility = View.VISIBLE
                            orientadorEt.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }

        val salvarBtn = findViewById<Button>(R.id.salvarBtn)
        salvarBtn.setOnClickListener {
            val nome = findViewById<EditText>(R.id.nomeEt).text.toString()
            val email = findViewById<EditText>(R.id.emailEt).text.toString()
            val celular = findViewById<EditText>(R.id.celularEt).text.toString()
            val nascimento = findViewById<EditText>(R.id.nascimentoEt).text.toString()
            val formacao = findViewById<Spinner>(R.id.formacaoSp).selectedItem.toString()
            val anoFormatura = findViewById<EditText>(R.id.anoFormaturaEt).text.toString()
            val instituicao = findViewById<EditText>(R.id.instituicaoEt).text.toString()
            val tituloMonografia = findViewById<EditText>(R.id.tituloMonografiaEt).text.toString()
            val orientador = findViewById<EditText>(R.id.orientadorEt).text.toString()
            val vagasInteresse = findViewById<EditText>(R.id.vagasInteresseEt).text.toString()

            val mensagem =
                """
        Nome: $nome
        Email: $email
        Celular: $celular
        Data de Nascimento: $nascimento
        Formação: $formacao
        Ano de Formatura: $anoFormatura
        Instituição: $instituicao
        Título da Monografia: $tituloMonografia
        Orientador: $orientador
        Vagas de Interesse: $vagasInteresse
    """
                    .trimIndent()

            AlertDialog.Builder(this)
                .setTitle("Informações Preenchidas")
                .setMessage(mensagem)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        val limparBtn = findViewById<Button>(R.id.limparBtn)
        limparBtn.setOnClickListener {
            findViewById<EditText>(R.id.nomeEt).text.clear()
            findViewById<EditText>(R.id.emailEt).text.clear()
            findViewById<EditText>(R.id.celularEt).text.clear()
            findViewById<EditText>(R.id.nascimentoEt).text.clear()
            findViewById<Spinner>(R.id.formacaoSp).setSelection(0)
            findViewById<EditText>(R.id.anoFormaturaEt).text.clear()
            findViewById<EditText>(R.id.instituicaoEt).text.clear()
            findViewById<EditText>(R.id.tituloMonografiaEt).text.clear()
            findViewById<EditText>(R.id.orientadorEt).text.clear()
            findViewById<EditText>(R.id.vagasInteresseEt).text.clear()
        }
    }
}
