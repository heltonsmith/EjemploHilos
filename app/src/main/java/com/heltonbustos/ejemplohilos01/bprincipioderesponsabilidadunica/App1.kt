package com.heltonbustos.ejemplohilos01.bprincipioderesponsabilidadunica

fun main(){

    var hilo1 = HiloPropioPRU()
    hilo1.crearHilo(1, 5000)

    var hilo2 = HiloPropioPRU()
    hilo2.crearHilo(2, 7000)

}