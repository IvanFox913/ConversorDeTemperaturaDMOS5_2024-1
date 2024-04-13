package br.edu.ifsp.dmo.conversordetemperatura.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.conversordetemperatura.R
import br.edu.ifsp.dmo.conversordetemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversordetemperatura.model.CelsiusToFStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.CelsiusToKStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.FahrenheitToCStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.FahrenheitToKStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.KelvinToCStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.KelvinToFStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.TemperatureConverter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStategy: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    /*
    * Diferente dos projetos anteriores, nesse exemplo o clique nos botões
    * é tratado com a implementação por uma lambda. As duas formas de
    * implementação indicadas são válidas, no botão btnCelsius adotou-se
    * a forma mais direta de implementação, sem especificar qual a
    * interface se está implementando. Já para o btnFahrenheit utiliza-se
    * uma implementação que indica a interface que está sendo implementada.
    *
    * Isso é possível pois é feita a inferência do tipo do argumento do método
    * setClickListener(), que sempre recebe um objeto de View.OnClickListener.
    *
    * Para cada botão é realizada a chamada do método handleConversion() que
    * recebe como argumento qual a estratégia que o sistema deve utilizar
    * para realizar a conversão.
    */
    private fun setClickListener(){
        binding.btnCelsiusToF.setOnClickListener {
            handleConversion(CelsiusToFStrategy)
        }

        binding.btnCelsiusToK.setOnClickListener {
            handleConversion(CelsiusToKStrategy)
        }

        binding.btnFahrenheitToC.setOnClickListener {
            handleConversion(FahrenheitToCStrategy)
        }

        binding.btnFahrenheitToK.setOnClickListener {
            handleConversion(FahrenheitToKStrategy)
        }

        binding.btnKelvinToC.setOnClickListener {
            handleConversion(KelvinToCStrategy)
        }

        binding.btnKelvinToF.setOnClickListener {
            handleConversion(KelvinToFStrategy)
        }
    }

    /*
    * Método responsável por recuperar o valor digitado no edittext
    * e converte-lo para o tipo Double.
    * O método pode lanãr uma exceção caso não seja possível converter
    * a entrada para Double.
    */
    private fun readTemperature(): Double {
        return try {
            binding.edittextTemperature.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            throw NumberFormatException("InputError")
        }
    }

    /*
    * O método agrupa a lógica da MainActivity, delegando à TemperatureConverter
    * a lógica de negócio da aplicação, contudo é MainActivity que aplica o
    * resultado da estratégia selecionada.
    */
    private fun handleConversion(strategy: TemperatureConverter) {
        converterStategy = strategy

        try {
            val inputValue = readTemperature()
            binding.textviewResultNumber.text = String.format(
                "%.2f %s",
                converterStategy.converter(inputValue),
                converterStategy.getScale()
            )

            binding.textviewResultMessage.text =

                when (this.converterStategy) {
                    is CelsiusToFStrategy -> {
                        getString(R.string.msgCtoF)
                    }

                    is CelsiusToKStrategy -> {
                        getString(R.string.msgCtoK)
                    }

                    is FahrenheitToCStrategy -> {
                        getString(R.string.msgFtoC)
                    }

                    is FahrenheitToKStrategy -> {
                        getString(R.string.msgFtoK)
                    }

                    is KelvinToCStrategy -> {
                        getString(R.string.msgKtoC)
                    }

                    else -> {
                        getString(R.string.msgKtoF)
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_popup_notify), Toast.LENGTH_SHORT).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }
    }
}