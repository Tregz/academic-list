package com.tregz.mvc.data.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.tregz.mvc.R;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.data.DataModel;

import java.util.Date;

public class ItemApple extends ItemModel {

    private String TAG = ItemApple.class.getSimpleName();

    // Color

    private int color = R.color.colorPrimary;

    private boolean wild = false;

    public int getColor() {
        return color;
    }

    public void setColor(int resource) {
        for (AppleColor color : AppleColor.values()) {
            if (color.getResource() == resource) {
                this.color = resource;
                return;
            }
        }
        Log.e(TAG, "Error: this color (" + resource + ") is not supported");
    }

    private enum AppleColor {
        GREEN(R.color.colorPrimary),
        GREEN_DARK(R.color.colorPrimaryDark),
        PINK(R.color.colorAccent);

        private int resource;

        public int getResource() {
            return resource;
        }

        AppleColor(int resource) {
            this.resource = resource;
        }
    }

    // Ripe

    private Date ripe;

    public Date getRipe() {
        return ripe;
    }

    public void setRipe(Date ripe) {
        this.ripe = ripe;
    }

    public ItemApple(Date ripe) {
        this.ripe = ripe;
    }

    @Override
    public float getPrice() {
        return super.getPrice() * (getWeight() >= 50 ? 2 : 1);
    }

    public ItemApple(boolean wild) {
        setPrice(0.01f);
        this.wild = wild;
    }

    public boolean edible() {
        if (ripe == null) return false;
        int bestBefore = 1; // one month
        return new Date().before(DateUtil.addMonth(ripe, bestBefore));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(color);
        parcel.writeSerializable(ripe);
        parcel.writeInt(wild ? 1 : 0);
    }

    private ItemApple(Parcel parcel) {
        color = parcel.readInt();
        ripe = (Date) parcel.readSerializable();
        wild = parcel.readInt() == 1;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<ItemApple>() {
        @Override
        public ItemApple createFromParcel(Parcel parcel) {
            return new ItemApple(parcel);
        }

        @Override
        public ItemApple[] newArray(int i) {
            return new ItemApple[i];
        }
    };
}
