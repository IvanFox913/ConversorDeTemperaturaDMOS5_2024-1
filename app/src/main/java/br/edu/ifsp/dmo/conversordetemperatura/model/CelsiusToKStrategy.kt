package br.edu.ifsp.dmo.conversordetemperatura.model

object CelsiusToKStrategy: TemperatureConverter {

    override fun converter(temperature: Double) = temperature + 273.15

    override fun getScale() = "°K"
}