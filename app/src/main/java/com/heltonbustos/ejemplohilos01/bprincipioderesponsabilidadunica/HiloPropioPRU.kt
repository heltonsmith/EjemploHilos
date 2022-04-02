package com.heltonbustos.ejemplohilos01.bprincipioderesponsabilidadunica

class HiloPropioPRU {

    fun crearHilo(num:Int, dormir:Long){
        var hilo = Thread(Runnable {
            var seg = dormir/1000
            println("HILO SECUNDARIO $num")
            println("HILO SECUNDARIO $num DORMIDO POR $seg SEGUNDOS")
            Thread.sleep(dormir)
            println("HILO SECUNDARIO $num HA TERMINADO")
        })
        hilo.start()
    }

}