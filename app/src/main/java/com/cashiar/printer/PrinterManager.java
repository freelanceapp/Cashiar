package com.cashiar.printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.cashiar.share.Common;
import com.cashiar.ui.activity_bill_Sell.BillSellActivity;


public class PrinterManager {
    public static final int REQUEST_ENABLE_BT = 2;
    public static BluetoothService mService = null;
    public static BluetoothDevice con_dev = null;
    public static final int REQUEST_CONNECT_DEVICE = 1;
    public static final String ENCODING = "GBK";
    private static final int D58MMWIDTH = 384;
    private static final int D80MMWIDTH = 576;


    public void connect(BillSellActivity currentActivity) {

        try {
            if (mService.isAvailable()) {
                if (currentActivity != null) {
                    if (!mService.isBTopen()) {
                        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        currentActivity.startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                    }
                    else { 
                        //Common.CreateDialogAlert(currentActivity,currentActivity.getString(R.string.open_bluetooth));
                    }
                }
            } else {
                Toast.makeText(currentActivity, Constants.no_BT_adapter, Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(currentActivity, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void connectDevice(String address){
        con_dev = mService.getDevByMac(address);
        mService.connect(con_dev);
    }


    void disconnect() {
        if (mService != null)
            mService.stop();
    }


//    private void printImageFromFilePath(BillDetailsActivity context, Bitmap bitmap) {
//
//        byte[] sendData = null;
//
//        Bitmap bm = bitmap;
//        if (bm != null) {
//            int height = D80MMWIDTH * bm.getHeight() / bm.getWidth();
//            bm = Bitmap.createScaledBitmap(bm, D80MMWIDTH, height, false);
//            sendData = PrintPicture.POS_PrintBMP(bm, D80MMWIDTH, 0);
//            mService.write(sendData);
//        } else {
//          //  Toast.makeText(context, Constants.no_file, Toast.LENGTH_SHORT).show();
//        }
//    }

//    public void printImage(BillDetailsActivity activity,Bitmap bitmap, Callback callback) {
//
//        if (mService.getState() == BluetoothService.STATE_CONNECTED) {
//            printImageFromFilePath(activity, bitmap);
//            callback.invoke("connected");
//        } else {
//            Toast.makeText(activity, Constants.notConnected, Toast.LENGTH_SHORT).show();
//            connect(activity);
//            callback.invoke("disconnected");
//        }
//    }



}
