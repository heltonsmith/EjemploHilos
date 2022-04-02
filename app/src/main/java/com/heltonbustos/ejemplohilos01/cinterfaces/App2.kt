package com.heltonbustos.ejemplohilos01.cinterfaces

fun main(){

    var peli = HiloPropioPRU2("La Máscara")
    peli.descargarPelicula(object: ListenerDescarga{
        override fun descargaFinalizada() {
            println("Película ${peli.pelicula} descargada")
            println("Play a la pelicula ${peli.pelicula}")
        }
    })

}