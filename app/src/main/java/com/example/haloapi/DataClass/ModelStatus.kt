package com.example.haloapi.DataClass

import com.google.gson.annotations.SerializedName

data class ModelStatus(
    @SerializedName("data"       ) var data       : Data?       = Data(),
    @SerializedName("additional" ) var additional : Additional? = Additional()
)
