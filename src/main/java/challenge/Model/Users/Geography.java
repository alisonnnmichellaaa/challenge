package challenge.Model.Users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geography {

    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;

    public Geography() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
