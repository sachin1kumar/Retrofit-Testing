package com.manager.retrofitesting.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class MainData implements Parcelable {

    @SerializedName("data")
    private Data[] data;

    @SerializedName("status")
    private String status;

    protected MainData(Parcel in) {
        data = in.createTypedArray(Data.CREATOR);
        status = in.readString();
    }

    public static final Creator<MainData> CREATOR = new Creator<MainData>() {
        @Override
        public MainData createFromParcel(Parcel in) {
            return new MainData(in);
        }

        @Override
        public MainData[] newArray(int size) {
            return new MainData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Data[] getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(data, i);
        parcel.writeString(status);
    }
}
