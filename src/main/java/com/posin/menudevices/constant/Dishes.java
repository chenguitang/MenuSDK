package com.posin.menudevices.constant;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Greetty on 2018/5/17.
 * <p>
 * 菜品实体类
 */
@SuppressLint("ParcelCreator")
public class Dishes implements Parcelable {

    //菜名
    private String dishName;
    //数量
    private int amount;
    //单价
    private double prices;
    //小计
    private double subtotal;

    public Dishes(String dishName, int amount, double prices, double subtotal) {
        this.dishName = dishName;
        this.amount = amount;
        this.prices = prices;
        this.subtotal = subtotal;
    }

    //从Parcel中读出之前写进的数据
    protected Dishes(Parcel in) {
        this.dishName = in.readString();
        this.amount=in.readInt();
        this.prices = in.readDouble();
        this.subtotal = in.readDouble();
    }

    public static final Creator<Dishes> CREATOR = new Creator<Dishes>() {
        //反序列化，把我们通过writeToParcel方法写进的数据再读出来
        @Override
        public Dishes createFromParcel(Parcel in) {
            return new Dishes(in);
        }

        @Override
        public Dishes[] newArray(int size) {
            return new Dishes[size];
        }
    };

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    //实现 Parcelable 接口所需重写的方法1，一般不用管
    @Override
    public int describeContents() {
        return 0;
    }

    //实现 Parcelable 接口所需重写的方法2，将对象的每个字段写入到Parcel中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishName);
        dest.writeInt(amount);
        dest.writeDouble(prices);
        dest.writeDouble(subtotal);
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "dishName='" + dishName + '\'' +
                ", amount=" + amount +
                ", prices=" + prices +
                ", subtotal=" + subtotal +
                '}';
    }
}
