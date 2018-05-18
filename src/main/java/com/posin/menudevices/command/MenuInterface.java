package com.posin.menudevices.command;

import com.posin.menudevices.constant.Dishes;

import java.util.List;

/**
 * Created by Greetty on 2018/5/18.
 * <p>
 * MenuInterface
 */
public interface MenuInterface {


    /**
     * 初始化SDK
     *
     * @param maxShowItem 菜单栏最大显示行数
     * @param isChinese   是否为中文
     * @param connectCallback    回调方法
     * @throws Exception
     */
    void initConnect(int maxShowItem, boolean isChinese, ConnectCallback connectCallback) throws Exception;


    /**
     * 设置菜单栏菜品
     *
     * @param listDishes 菜品集合，如果集合大于最大显示行数，则默认显示后添加的菜品
     * @param sum        总计金额
     * @throws Exception
     */
    void sendMenu(List<Dishes> listDishes, double sum) throws Exception;

    /**
     * 支付
     *
     * @param sum         总计金额
     * @param discountSum 优惠金额
     * @param alreadyPay  已付金额
     * @param giveChange  找零金额
     * @throws Exception
     */
    void pay(double sum, double discountSum, double alreadyPay, double giveChange) throws Exception;

    /**
     * 清空菜单栏
     *
     * @throws Exception
     */
    void clearMenu() throws Exception;
}
