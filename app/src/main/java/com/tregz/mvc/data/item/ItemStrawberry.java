package com.tregz.mvc.data.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.tregz.mvc.R;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.data.DataModel;
import com.tregz.mvc.data.user.UserModel;

import java.util.Date;

public class ItemStrawberry extends ItemModel {

    private String TAG = ItemStrawberry.class.getSimpleName();

    // Color

    private int color = R.color.colorPrimary;

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

    public ItemStrawberry(Date ripe) {
        this.ripe = ripe;
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
    }

    private ItemStrawberry(Parcel parcel) {
        color = parcel.readInt();
        ripe = (Date) parcel.readSerializable();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<ItemStrawberry>() {
        @Override
        public ItemStrawberry createFromParcel(Parcel parcel) {
            return new ItemStrawberry(parcel);
        }

        @Override
        public ItemStrawberry[] newArray(int i) {
            return new ItemStrawberry[i];
        }
    };
}
