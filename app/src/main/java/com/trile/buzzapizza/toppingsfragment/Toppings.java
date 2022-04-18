package com.trile.buzzapizza.toppingsfragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.trile.buzzapizza.R;

class Toppings implements Parcelable {
    public enum Type {
        PEPPERONI(R.drawable.ic_topping_pepperoni),
        BACON(R.drawable.ic_topping_bacon),
        MUSHROOMS(R.drawable.ic_topping_mushrooms),
        TOMATOES(R.drawable.ic_topping_tomatoes),
        OLIVES(R.drawable.ic_topping_olives),
        GREEN_PEPPERS(R.drawable.ic_topping_green_peppers),
        ONIONS(R.drawable.ic_topping_onions),
        JALAPENOS(R.drawable.ic_topping_jalapenos);

        private int drawableResourceId;

        public int getDrawableResourceId() {
            return this.drawableResourceId;
        }

        Type(int drawableResourceId) {
            this.drawableResourceId = drawableResourceId;
        }
    }

    private String name;

    public Toppings(String name) {
        this.name = name;
    }

    protected Toppings(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Toppings> CREATOR = new Creator<Toppings>() {
        @Override
        public Toppings createFromParcel(Parcel in) {
            return new Toppings(in);
        }

        @Override
        public Toppings[] newArray(int size) {
            return new Toppings[size];
        }
    };


    public int getDrawableResourceId() {
        if (this.name.equalsIgnoreCase(Type.PEPPERONI.name())) {
            return Type.PEPPERONI.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.BACON.name())) {
            return Type.BACON.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.MUSHROOMS.name())) {
            return Type.MUSHROOMS.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.TOMATOES.name())) {
            return Type.TOMATOES.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.OLIVES.name())) {
            return Type.OLIVES.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.GREEN_PEPPERS.name())) {
            return Type.GREEN_PEPPERS.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.ONIONS.name())) {
            return Type.ONIONS.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.JALAPENOS.name())) {
            return Type.JALAPENOS.getDrawableResourceId();
        }
        return Type.TOMATOES.getDrawableResourceId();
    }
}
