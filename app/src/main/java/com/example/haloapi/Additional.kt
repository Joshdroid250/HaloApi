package com.example.haloapi

import com.google.gson.annotations.SerializedName

data class Additional(
    @SerializedName("parameters" ) var parameters : Parameters? = Parameters()
)
