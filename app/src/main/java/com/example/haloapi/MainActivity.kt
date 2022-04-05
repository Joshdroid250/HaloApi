package com.example.haloapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.haloapi.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            buscarStatsPlayer()
        }
    }
    private fun getRetrofit(): Retrofit {
        val clientOk = OkHttpClient.Builder().addInterceptor { chain ->
            val token: String = "tok_dev_upH3fXJSDS3x5QD2zGzrNBU8BQxmb4nvUu7JkanRnbAyRbyHnoqbRcoQVTkqgQ2u"
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()
        return Retrofit
            .Builder()
            .baseUrl("https://halo.api.stdlib.com/infinite@1.0.0/appearance/players/spartan-id/")
            .client(clientOk)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarStatsPlayer(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val txtFiltro: String = binding.txBuscar.text.toString().lowercase()
                val call = getRetrofit().create(ApiService::class.java).getStatsHalo("?gamertag=$txtFiltro")
                if (call.isSuccessful){
                    val serviceTag:String = call.body()?.data?.serviceTag.toString()
                    val emblem: String = call.body()?.data?.emblemUrl.toString()
                    val backdrop: String = call.body()?.data?.backdropImageUrl.toString()
                    val gamer: String = call.body()?.additional?.parameters?.gamertag.toString()
                    binding.txtService.text = "Service tag: $serviceTag"
                    binding.txGamerTag.text = "$gamer"
                    Picasso.get().load(emblem).into(binding.imgEmblem)
                    Picasso.get().load(backdrop).into(binding.ivBackDrop)
                }
            }catch (ex: Exception){
                val msn = Toast.makeText(this@MainActivity, "Error de conexion", Toast.LENGTH_LONG)
                msn.setGravity(Gravity.CENTER, 0, 0)
                msn.show()
            }
        }
    }
}