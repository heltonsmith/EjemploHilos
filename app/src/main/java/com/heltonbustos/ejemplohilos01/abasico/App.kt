package com.heltonbustos.ejemplohilos01.abasico

fun main(){

    //hilo principal
    println("HILO PRINCIPAL")
    println("ESPERANDO 0 SEGUNDOS EL HILO PRINCIPAL")
    println("TERMINÓ EL HILO PRINCIPAL")
    //hilo principal

    crearHilo(1, 2000) //descargar una pelicula
    crearHilo(2, 5000) //descargar las imagenes de la interfaz
    crearHilo(3, 3000) //leyendo las palabras y subrayandolas con rojo

}

fun crearHilo(num:Int, dormir:Long){
    var hilo = Thread(Runnable {
        println("HILO SECUNDARIO $num")
        println("HILO SECUNDARIO $num DORMIDO POR 3 SEGUNDOS")
        Thread.sleep(dormir)
        println("HILO SECUNDARIO $num HA TERMINADO")
    })
    hilo.start()
}