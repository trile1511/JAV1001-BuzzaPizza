package com.trile.buzzapizza.toppingsfragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.trile.buzzapizza.R;

public class Topping implements Parcelable {
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

    public Topping(String name) {
        this.name = name;
    }

    protected Topping(Parcel in) {
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

    public static final Creator<Topping> CREATOR = new Creator<Topping>() {
        @Override
        public Topping createFromParcel(Parcel in) {
            return new Topping(in);
        }

        @Override
        public Topping[] newArray(int size) {
            return new Topping[size];
        }
    };

    public String getName() {
        return name;
    }

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
        } else if (this.name.equalsIgnoreCase(
                TextUtils.join(
                        " ",
                        Type.GREEN_PEPPERS.name().split("_")
                )
        )) {
            return Type.GREEN_PEPPERS.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.ONIONS.name())) {
            return Type.ONIONS.getDrawableResourceId();
        } else if (this.name.equalsIgnoreCase(Type.JALAPENOS.name())) {
            return Type.JALAPENOS.getDrawableResourceId();
        }
        return Type.TOMATOES.getDrawableResourceId();
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Topping topping = (Topping) obj;
        return this.name.equalsIgnoreCase(topping.name);
    }
}
