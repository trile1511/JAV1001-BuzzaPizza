package com.trile.buzzapizza.homefragment;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderItem implements Parcelable {
    private List<String> toppings = new ArrayList();
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String toppingsText;

    public HistoryOrderItem(
            List<String> toppings,
            String name,
            String address,
            String city,
            String zipCode
    ) {
        this.toppings.addAll(toppings);

        // Convert from array list of toppings to a string with comma-separated toppings
        // ["Mozzarella", "BBQ Sauce", "Tomato"] => "Mozzarella, BBQ Sauce, and Tomato"
        List<String> toppingsToConvert = new ArrayList<>(this.toppings);
        String firstTopping = toppingsToConvert.get(0);
        String lastTopping = toppingsToConvert.get(toppingsToConvert.size() - 1);
        // Capitalize first letter of first topping in the list
        toppingsToConvert.set(
                0,
                String.format(
                        "%s%s",
                        firstTopping.substring(0, 1).toUpperCase(),
                        firstTopping.substring(1)
                )
        );
        // Add "and" as a linking word to the last topping in the list for a more natural sentence.
        toppingsToConvert.set(toppingsToConvert.size() - 1, String.format("and %s.", lastTopping));

        String text = toppingsToConvert.toString();
        this.toppingsText = text.substring(1, text.length() - 1);   // Remove the square brackets []
    }

    protected HistoryOrderItem(Parcel in) {
        toppings = in.createStringArrayList();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        zipCode = in.readString();
        toppingsText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(toppings);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(zipCode);
        dest.writeString(toppingsText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoryOrderItem> CREATOR = new Creator<HistoryOrderItem>() {
        @Override
        public HistoryOrderItem createFromParcel(Parcel in) {
            return new HistoryOrderItem(in);
        }

        @Override
        public HistoryOrderItem[] newArray(int size) {
            return new HistoryOrderItem[size];
        }
    };

    public List<String> getToppings() {
        return toppings;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getToppingsText() {
        return toppingsText;
    }
}
