package com.heltonbustos.ejemplohilos01.eactivity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.heltonbustos.ejemplohilos01.R

class MainActivity : AppCompatActivity() {
    lateinit var txtHilo1:TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHilo1 = findViewById(R.id.txtHilo1)
        progressBar = findViewById(R.id.progressBar)

        var btnIniciarHiloBloqueado:Button = findViewById(R.id.btnIniciarHiloBloqueado)
        var btnIniciarHiloSegundoPlano:Button = findViewById(R.id.btnIniciarHiloSegundoPlano)
        var btnIniciarHiloSegundoPlanoUI:Button = findViewById(R.id.btnIniciarHiloSegundoPlanoUI)
        var btnIniciarHiloSegundoPlanoAsyncTask:Button = findViewById(R.id.btnIniciarHiloSegundoPlanoAsyncTask)

        btnIniciarHiloBloqueado.setOnClickListener {
            bloquearHilo()
        }

        btnIniciarHiloSegundoPlano.setOnClickListener {
            crearHiloSecundario()
        }

        btnIniciarHiloSegundoPlanoUI.setOnClickListener {
            crearHiloSecundarioUI()
        }

        btnIniciarHiloSegundoPlanoAsyncTask.setOnClickListener {
            var hilo = Hilo()
            hilo.execute()
        }

    }

     //simula una carga pesada en la actividad
    fun bloquearHilo(){
        for(i in 0.. 20){
            Thread.sleep(1000)
            Log.e("Trabajo Hilo", "Iteración: $i")
        }
    }

    //hilo secundario - simular un error en la interfaz
    fun crearHiloSecundario(){
        var h = Thread(Runnable {
            for(i in 0.. 20){
                Thread.sleep(1000)
                Log.e("Trabajo Hilo Secundario", "Iteración: $i")
                Toast.makeText(this, "Iteración: $i", Toast.LENGTH_SHORT).show()
            }
        })
        h.start()
    }

    //hilo secundario - con comunicación de UI
    fun crearHiloSecundarioUI(){
        var h = Thread(Runnable {
            for(i in 0.. 20){
                Thread.sleep(1000)
                Log.e("Trabajo Hilo Secundario", "Iteración: $i")
                runOnUiThread(Runnable {
                    Toast.makeText(this, "Iteración: $i", Toast.LENGTH_SHORT).show()
                })
            }
        })
        h.start()
    }

    inner class Hilo: AsyncTask<Int, Int, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Int?): String {
            txtHilo1.text = "Hilo Inicia..."
            publishProgress()
            return ""
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.visibility = View.VISIBLE
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            progressBar.visibility = View.GONE
            Toast.makeText(applicationContext, "COMPLETADO", Toast.LENGTH_SHORT).show()
            super.onPostExecute(result)
        }
    }

}