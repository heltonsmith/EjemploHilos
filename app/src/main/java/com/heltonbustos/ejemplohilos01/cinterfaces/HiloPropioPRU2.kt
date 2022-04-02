package com.heltonbustos.ejemplohilos01.cinterfaces

class HiloPropioPRU2(var pelicula:String){

    fun descargarPelicula(listener: ListenerDescarga){
        var hilo = Thread(Runnable {
            println("Descargando película $pelicula")
            println("Esperando descarga de $pelicula")
            Thread.sleep(5000)
            listener.descargaFinalizada()
        })
        hilo.start()
    }

}