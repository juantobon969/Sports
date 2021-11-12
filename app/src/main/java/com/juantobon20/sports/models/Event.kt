package com.juantobon20.sports.models

import com.google.gson.annotations.SerializedName
import com.juantobon20.sports.utils.ListAdapterItem

data class Event(
    @SerializedName("idEvent") override val id: String?,
    @SerializedName("strEvent") val description: String?,
    @SerializedName("strLeague") val league: String?
): ListAdapterItem
