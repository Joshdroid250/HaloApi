package com.example.haloapi.DataClass

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("emblem_url"         ) var emblemUrl        : String? = null,
    @SerializedName("backdrop_image_url" ) var backdropImageUrl : String? = null,
    @SerializedName("service_tag"        ) var serviceTag       : String? = null
)
