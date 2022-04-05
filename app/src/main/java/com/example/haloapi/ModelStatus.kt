package com.example.haloapi

import com.google.gson.annotations.SerializedName

data class ModelStatus(
    @SerializedName("data"       ) var data       : Data?       = Data(),
    @SerializedName("additional" ) var additional : Additional? = Additional()
)
