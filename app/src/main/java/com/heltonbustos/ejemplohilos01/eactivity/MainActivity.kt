package com.heltonbustos.ejemplohilos01.eactivity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.heltonbustos.ejemplohilos01.R
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var txtHilo1:TextView
    lateinit var progressBar: ProgressBar
    lateinit var progressBar2: ProgressBar
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHilo1 = findViewById(R.id.txtHilo1)
        progressBar = findViewById(R.id.progressBar)
        progressBar2 = findViewById(R.id.progressBar2)
        imageView = findViewById(R.id.imageView)

        var btnIniciarHiloBloqueado:Button = findViewById(R.id.btnIniciarHiloBloqueado)
        var btnIniciarHiloSegundoPlano:Button = findViewById(R.id.btnIniciarHiloSegundoPlano)
        var btnIniciarHiloSegundoPlanoUI:Button = findViewById(R.id.btnIniciarHiloSegundoPlanoUI)
        var btnIniciarHiloSegundoPlanoAsyncTask:Button = findViewById(R.id.btnIniciarHiloSegundoPlanoAsyncTask)
        var btnDescargarImagen:Button = findViewById(R.id.btnDescargarImagen)

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

        btnDescargarImagen.setOnClickListener {
            DescargarImagen().execute(
                "https://besthqwallpapers.com/Uploads/23-1-2021/153540/thumb2-skyline-cityscapes-4k-fantasy-art-comets-skyscrapers.jpg")
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

    /**
     * Hilo de progressBar horizontal (barra)
     */
    inner class Hilo: AsyncTask<Int, Int, String>() {
        override fun onPreExecute() {
            progressBar.progress = 0
            progressBar.max = 100
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Int?): String? {
            //consultar la bd
            txtHilo1.text = "Hilo Inicia..."
            for(i in 0.. 100){
                publishProgress(i)
                Thread.sleep(10)
            }
            return "COMPLETADO"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            super.onPostExecute(result)
        }
    }

    /**
     * Hilo de descargar imagen
     */
    inner class DescargarImagen: AsyncTask<String, Int, Bitmap>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): Bitmap? {
            //consultar la bd
            txtHilo1.text = "Descarga de la imagen Inicia..."
            publishProgress()
            val url = URL(p0[0])
            val input = url.openStream()
            val imagen: Bitmap = BitmapFactory.decodeStream(input)

            return imagen
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar2.visibility = View.VISIBLE
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Bitmap?) {
            progressBar2.visibility = View.GONE
            imageView.setImageBitmap(result)
            Toast.makeText(applicationContext, "Imagen descargada OK", Toast.LENGTH_SHORT).show()
            super.onPostExecute(result)
        }
    }

}