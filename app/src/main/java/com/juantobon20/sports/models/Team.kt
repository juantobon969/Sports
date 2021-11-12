package com.juantobon20.sports.models

import com.google.gson.annotations.SerializedName
import com.juantobon20.sports.utils.ListAdapterItem

data class Team(
    @SerializedName("idTeam") override val id: String?,
    @SerializedName("strTeam") val name: String?,
    @SerializedName("strAlternate") val nameAlt: String?,
    @SerializedName("strDescriptionES") val description: String?,
    @SerializedName("strTeamBadge") val badge: String?,
    @SerializedName("strTeamJersey") val jersey: String?,
    @SerializedName("strStadium") val stadium: String?,
    @SerializedName("intFormedYear") val foundationYear: String?,
    @SerializedName("strFacebook") val webFacebook: String?,
    @SerializedName("strInstagram") val webInstagram: String?,
    @SerializedName("strTwitter") val webTwitter: String?
) : ListAdapterItem
