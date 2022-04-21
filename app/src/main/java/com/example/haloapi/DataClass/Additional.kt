package com.example.haloapi.DataClass

import com.example.haloapi.DataClass.Parameters
import com.google.gson.annotations.SerializedName

data class Additional(
    @SerializedName("parameters" ) var parameters : Parameters? = Parameters()
)
