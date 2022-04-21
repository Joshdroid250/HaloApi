package com.example.haloapi.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haloapi.ApiService
import com.example.haloapi.databinding.FragmentPrincipalScreenBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class principal_screen : Fragment() {

    lateinit var binding: FragmentPrincipalScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentPrincipalScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         fun getRetrofit(): Retrofit {
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


          fun buscarStatsPlayer(){
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
                        binding.txGamertag.text = "$gamer"
                        Picasso.get().load(emblem).into(binding.imgEmblem)
                        Picasso.get().load(backdrop).into(binding.ivBackDrop)
                    }
                }catch (ex: Exception){

                }
            }
        }
        with(binding){
            button.setOnClickListener {
                buscarStatsPlayer()
            }
        }

    }
}