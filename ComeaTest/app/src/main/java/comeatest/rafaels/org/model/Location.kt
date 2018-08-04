package comeatest.rafaels.org.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Location {

    @SerializedName("lng")
    @Expose
    var lng: Double? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("lng", lng)
                .append("lat", lat)
                .toString()
    }

}
