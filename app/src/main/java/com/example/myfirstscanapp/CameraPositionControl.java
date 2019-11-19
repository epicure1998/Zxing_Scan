package com.example.myfirstscanapp;

import android.content.Context;

import com.segway.robot.sdk.base.bind.ServiceBinder;
import com.segway.robot.sdk.locomotion.head.Head;

public class CameraPositionControl {
    private Head mHead;
    private ServiceBinder.BindStateListener mHeadBindStateListener;
    private Boolean mHeadBind;
    private Context context;

    private void bindService(){
        this.mHead.bindService(this.context, mHeadBindStateListener);
    }

    public CameraPositionControl(Context context) {
        this.mHead = Head.getInstance();
        this.mHeadBindStateListener  = new ServiceBinder.BindStateListener() {
            @Override
            public void onBind() {
                mHeadBind = true;
            }

            @Override
            public void onUnbind(String reason) {
                mHeadBind = false;
            }
        };
    }

    public void turnCameraOn(){
        mHead.setMode(Head.MODE_SMOOTH_TACKING);
        mHead.setWorldPitch((float) Math.PI/2);

    }


}
