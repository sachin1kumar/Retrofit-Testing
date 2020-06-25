package com.manager.retrofitesting.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {

    @SerializedName("profile_image")
    private String profile_image;

    @SerializedName("employee_name")
    private String employee_name;

    @SerializedName("employee_salary")
    private String employee_salary;

    @SerializedName("id")
    private String id;

    @SerializedName("employee_age")
    private String employee_age;

    protected Data(Parcel in) {
        profile_image = in.readString();
        employee_name = in.readString();
        employee_salary = in.readString();
        id = in.readString();
        employee_age = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profile_image);
        dest.writeString(employee_name);
        dest.writeString(employee_salary);
        dest.writeString(id);
        dest.writeString(employee_age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    @Override
    public String toString() {
        return "ClassPojo [profile_image = " + profile_image + ", employee_name = " + employee_name + ", employee_salary = " + employee_salary + ", id = " + id + ", employee_age = " + employee_age + "]";
    }
}
