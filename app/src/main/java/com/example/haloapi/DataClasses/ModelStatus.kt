package com.example.haloapi.DataClasses

import com.example.haloapi.DataClasses.Additional
import com.example.haloapi.DataClasses.Data
import com.google.gson.annotations.SerializedName

data class ModelStatus(
    @SerializedName("data"       ) var data       : Data?       = Data(),
    @SerializedName("additional" ) var additional : Additional? = Additional()
)
