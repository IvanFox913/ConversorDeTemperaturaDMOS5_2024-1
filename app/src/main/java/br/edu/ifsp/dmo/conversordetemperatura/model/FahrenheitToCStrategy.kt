package br.edu.ifsp.dmo.conversordetemperatura.model

object FahrenheitToCStrategy: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return (temperature - 32) * 5/9
    }

    override fun getScale(): String {
        return "ºC"
    }

}