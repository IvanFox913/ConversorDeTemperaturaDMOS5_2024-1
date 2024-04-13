package br.edu.ifsp.dmo.conversordetemperatura.model

object CelsiusToFStrategy: TemperatureConverter {

    override fun converter(temperature: Double) = (temperature * 9/5) + 32

    override fun getScale() = "°F"
}