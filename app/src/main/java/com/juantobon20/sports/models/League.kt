package com.juantobon20.sports.models

import com.google.gson.annotations.SerializedName
import com.juantobon20.sports.utils.ListAdapterItem

data class League(
    @SerializedName("idLeague") override val id: String,
    @SerializedName("strLeague") val description: String,
    @SerializedName("strSport") val sport: String
): ListAdapterItem
