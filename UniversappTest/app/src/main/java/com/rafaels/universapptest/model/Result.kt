package com.rafaels.universapptest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Result {

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("name")
    @Expose
    var name: Name? = null

    @SerializedName("dob")
    @Expose
    var dob: Dob? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("picture")
    @Expose
    var picture: Picture? = null

}
