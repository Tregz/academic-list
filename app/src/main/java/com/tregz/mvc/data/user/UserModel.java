package com.tregz.mvc.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class UserModel implements Parcelable {
    public static final String TAG = UserModel.class.getSimpleName();

    public UserModel() {}

    private boolean allergic;
    private Date birthDate;
    private String email;
    private String firstName;
    private int gender;
    private String lastName;
    private long phoneNumber;

    public boolean isAllergic() {
        return allergic;
    }

    public void setAllergic(boolean allergic) {
        this.allergic = allergic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(allergic ? 1 : 0);
        parcel.writeSerializable(birthDate);
        parcel.writeString(email);
        parcel.writeString(firstName);
        parcel.writeInt(gender);
        parcel.writeString(lastName);
        parcel.writeLong(phoneNumber);
    }

    private UserModel(Parcel parcel) {
        allergic = parcel.readInt() == 1;
        birthDate = (Date) parcel.readSerializable();
        email = parcel.readString();
        firstName = parcel.readString();
        gender = parcel.readInt();
        lastName = parcel.readString();
        phoneNumber = parcel.readLong();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        @Override
        public UserModel[] newArray(int i) {
            return new UserModel[i];
        }
    };
}
