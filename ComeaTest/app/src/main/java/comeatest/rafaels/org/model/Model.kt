package comeatest.rafaels.org.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Model {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("logoUrl")
    @Expose
    var logoUrl: String? = null
    @SerializedName("openingHour")
    @Expose
    var openingHour: Int? = null
    @SerializedName("closingHour")
    @Expose
    var closingHour: Int? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("category")
    @Expose
    var category: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("name", name)
                .append("logoUrl", logoUrl)
                .append("openingHour", openingHour)
                .append("closingHour", closingHour)
                .append("location", location)
                .append("category", category)
                .append("id", id)
                .toString()
    }

}
