package br.edu.ifsp.dmo.conversordetemperatura.model

interface TemperatureConverter {

    /*
    * Método define o comportamento de todas as
    * classes que implementam a interface.
    */
    fun converter(temperature: Double): Double

    /*
    * Método retorna a escala da temperatura
    * convertida
    */
    fun getScale(): String
}