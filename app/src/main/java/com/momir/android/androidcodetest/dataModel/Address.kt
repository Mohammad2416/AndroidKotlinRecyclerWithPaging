package com.momir.android.androidcodetest.dataModel

import com.google.gson.annotations.SerializedName

data class Address (

    @SerializedName("street") val street : String,
    @SerializedName("suite") val suite : String,
    @SerializedName("city") val city : String,
    @SerializedName("zipcode") val zipCode : String,
    @SerializedName("geo") val geo : Geo

)

