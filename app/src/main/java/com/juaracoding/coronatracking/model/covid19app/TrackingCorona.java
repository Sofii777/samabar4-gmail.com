
package com.juaracoding.coronatracking.model.covid19app;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackingCorona implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<TrackingCorona> CREATOR = new Creator<TrackingCorona>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TrackingCorona createFromParcel(Parcel in) {
            return new TrackingCorona(in);
        }

        public TrackingCorona[] newArray(int size) {
            return (new TrackingCorona[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4615084094203567768L;

    protected TrackingCorona(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrackingCorona() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public TrackingCorona(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
