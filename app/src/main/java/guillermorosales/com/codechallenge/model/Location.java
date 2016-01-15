package guillermorosales.com.codechallenge.model;

public class Location {

    private Boolean needsRecoding;
    private String longitude;
    private String latitude;
    private String humanAddress;

    /**
     * @return The needsRecoding
     */
    public Boolean getNeedsRecoding() {
        return needsRecoding;
    }

    /**
     * @param needsRecoding The needs_recoding
     */
    public void setNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The humanAddress
     */
    public String getHumanAddress() {
        return humanAddress;
    }

    /**
     * @param humanAddress The human_address
     */
    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

}
