// IMenuManage.aidl
package com.posin.menudevices;

import com.posin.menudevices.constant.Dishes;
import com.posin.menudevices.ICallback;

// Declare any non-default types here with import statements

interface IMenuManage {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    //初始化数据
    //maxShowItem 菜单栏最大显示行数
    //isChinese是否为中文
    void init(int maxShowItem,boolean isChinese,ICallback callback);

    //设置菜单栏菜品
    //listDishes菜品集合，如果集合大于最大显示行数，则默认显示后添加的菜品
    //sum 总计金额
    void sendMenu(in List<Dishes> listDishes,double sum);

    //支付
    //sum 总计金额
    //discountSum 优惠金额
    //alreadyPay 已付金额
    //giveChange 找零金额
    void pay(double sum,double discountSum,double alreadyPay,double giveChange);

    //清空菜单栏
    void clearMenu();
}
