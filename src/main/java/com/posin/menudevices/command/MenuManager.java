package com.posin.menudevices.command;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.posin.menudevices.ICallback;
import com.posin.menudevices.IMenuManage;
import com.posin.menudevices.constant.Dishes;

import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Greetty on 2018/5/18.
 * MenuManager
 */
public class MenuManager implements MenuInterface {

    private static final String TAG = "MenuManager";
    private static IMenuManage mMenuManage;

    private static MenuManager mInstance;

    private static ConnectCallback mConnectCallback;

    /**
     * 单例模式获取MenuManager实例
     *
     * @param context Context
     * @return MenuManager
     */
    public static MenuManager getInstance(Context context) {
        if (mMenuManage == null) {
            bindService(context);
        }
        if (mInstance == null) {
            synchronized (MenuManager.class) {
                if (mInstance == null) {
                    mInstance = new MenuManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化SDK
     *
     * @param context Context
     */
    public static void init(Context context) {
        if (mMenuManage == null) {
            bindService(context);
        }
    }


    @Override
    public void initConnect(int maxShowItem, boolean isChinese, ConnectCallback connectCallback) throws Exception {
        if (mMenuManage == null) {
            throw new Exception("menu sdk not connect Advertising system ... ");
        }
        mConnectCallback = connectCallback;
        mMenuManage.init(maxShowItem, isChinese, mCallback);
    }

    @Override
    public void sendMenu(List<Dishes> listDishes, double sum) throws Exception {
        if (mMenuManage == null) {
            throw new Exception("menu sdk not connect Advertising system ... ");
        }
        mMenuManage.sendMenu(listDishes, sum);
    }

    @Override
    public void pay(double sum, double discountSum, double alreadyPay, double giveChange) throws Exception {
        if (mMenuManage == null) {
            throw new Exception("menu sdk not connect Advertising system ... ");
        }
        mMenuManage.pay(sum, discountSum, alreadyPay, giveChange);
    }

    @Override
    public void clearMenu() throws Exception {
        if (mMenuManage == null) {
            throw new Exception("menu sdk not connect Advertising system ... ");
        }
        mMenuManage.clearMenu();
    }

    /**
     * 绑定服务
     */
    private static void bindService(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.posin.menudevices.menuservices");
        intent.setPackage("com.posin.menudevices");
        context.bindService(intent, conn, BIND_AUTO_CREATE);

    }

    private static ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMenuManage = IMenuManage.Stub.asInterface(service);
            Log.e(TAG, "*****************************************************");
            Log.e(TAG, "ServiceConnection ...");
            Log.e(TAG, "*****************************************************");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMenuManage = null;
            Log.e(TAG, "*****************************************************");
            Log.e(TAG, "onServiceDisconnected ...");
            Log.e(TAG, "*****************************************************");
        }

    };

    private ICallback mCallback = new ICallback.Stub() {
        @Override
        public void success() throws RemoteException {
            if (mConnectCallback != null)
                mConnectCallback.success();

        }

        @Override
        public void failure() throws RemoteException {
            if (mConnectCallback != null)
                mConnectCallback.failure();
        }
    };

}
