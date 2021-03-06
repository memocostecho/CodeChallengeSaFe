package guillermorosales.com.codechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SFReportsModel implements Parcelable {

    public static final Creator<SFReportsModel> CREATOR = new Creator<SFReportsModel>() {
        @Override
        public SFReportsModel createFromParcel(Parcel in) {
            return new SFReportsModel(in);
        }

        @Override
        public SFReportsModel[] newArray(int size) {
            return new SFReportsModel[size];
        }
    };
    private String time;
    private String category;
    private String pddistrict;
    private Location location;
    private String address;
    private String descript;
    private String dayofweek;
    private String resolution;
    private String date;
    private String y;
    private String x;
    private String incidntnum;

    protected SFReportsModel(Parcel in) {
        time = in.readString();
        category = in.readString();
        pddistrict = in.readString();
        address = in.readString();
        descript = in.readString();
        dayofweek = in.readString();
        resolution = in.readString();
        date = in.readString();
        y = in.readString();
        x = in.readString();
        incidntnum = in.readString();
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The pddistrict
     */
    public String getPddistrict() {
        return pddistrict;
    }

    /**
     * @param pddistrict The pddistrict
     */
    public void setPddistrict(String pddistrict) {
        this.pddistrict = pddistrict;
    }

    /**
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The descript
     */
    public String getDescript() {
        return descript;
    }

    /**
     * @param descript The descript
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * @return The dayofweek
     */
    public String getDayofweek() {
        return dayofweek;
    }

    /**
     * @param dayofweek The dayofweek
     */
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    /**
     * @return The resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * @param resolution The resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y The y
     */
    public void setY(String y) {
        this.y = y;
    }

    /**
     * @return The x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x The x
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return The incidntnum
     */
    public String getIncidntnum() {
        return incidntnum;
    }

    /**
     * @param incidntnum The incidntnum
     */
    public void setIncidntnum(String incidntnum) {
        this.incidntnum = incidntnum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(category);
        dest.writeString(pddistrict);
        dest.writeString(address);
        dest.writeString(descript);
        dest.writeString(dayofweek);
        dest.writeString(resolution);
        dest.writeString(date);
        dest.writeString(y);
        dest.writeString(x);
        dest.writeString(incidntnum);
    }
}
