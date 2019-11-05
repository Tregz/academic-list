package com.tregz.mvc.data.item;

import com.tregz.mvc.data.DataModel;

public abstract class ItemModel extends DataModel {

    private float weight = 0;
    private float price = 0;

    public float getPrice() {
        return price * weight;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
