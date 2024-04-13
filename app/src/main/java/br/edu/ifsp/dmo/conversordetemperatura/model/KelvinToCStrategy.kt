package br.edu.ifsp.dmo.conversordetemperatura.model

object KelvinToCStrategy: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return temperature - 273.15
    }

    override fun getScale(): String {
        return "ºC"
    }
}