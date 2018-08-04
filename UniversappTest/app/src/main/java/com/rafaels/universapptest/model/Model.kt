package com.rafaels.universapptest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Model {

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("info")
    @Expose
    var info: Info? = null

}
