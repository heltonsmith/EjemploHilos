package com.heltonbustos.ejemplohilos01.dinterfaces

class HiloPropioPRU3(var pelicula:String) : ListenerDescarga2{

    fun descargarPelicula(){
        var hilo = Thread(Runnable {
            println("Descargando película $pelicula")
            println("Esperando descarga de $pelicula")
            mientrasSeDescarga()
            Thread.sleep(10000) //simulación de la descarga
            descargaFinalizada()
        })
        hilo.start()
    }

    override fun descargaFinalizada() {
        println("DESCARGA PELICULA ${pelicula} FINALIZADA")
        println("PLAY PELICULA ${pelicula}")
    }

    override fun mientrasSeDescarga() {
        println("CARGANDO...")
    }


}