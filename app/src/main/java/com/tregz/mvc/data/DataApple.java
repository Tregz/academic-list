package com.tregz.mvc.data;

import android.util.Log;

import com.tregz.mvc.R;
import com.tregz.mvc.core.date.DateUtil;

import java.util.Date;

public class DataApple {

    private String TAG = DataApple.class.getSimpleName();

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

    public DataApple(Date ripe) {
        this.ripe = ripe;
    }

    public boolean edible() {
        if (ripe == null) return false;
        int bestBefore = 1; // one month
        return new Date().before(DateUtil.addMonth(ripe, bestBefore));
    }
}
