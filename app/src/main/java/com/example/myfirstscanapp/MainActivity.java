package com.example.myfirstscanapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
    private Button scan_btn;
    private TextView textView;
    //private Button qr_btn;
    // private TextView textView=(TextView)findViewById(R.id.textView);
    @Override//初始化界面
    protected void onCreate(Bundle savedInstanceState) {
        CameraPositionControl cameraPositionControl = new CameraPositionControl(this.getApplicationContext());
        cameraPositionControl.turnCameraOn();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_btn=(Button)findViewById(R.id.scan_btn);
        final Activity activity=this;
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("Scaning data will be here");
        scan_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES );
                integrator.setPrompt("QRCode Scanning ...");
                integrator.setCameraId(0);//0是使用默认相机
                integrator.setBeepEnabled(true);//扫描后的提示音
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "you cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                System.out.println("::"+result.getContents().toString());
                Toast.makeText(this,"Successfully scanning"+ result.getContents() , Toast.LENGTH_LONG).show();
                textView=(TextView)findViewById(R.id.textView);
                CharSequence cs=result.getContents().subSequence(0, result.getContents().length());
                textView.setText(cs);

            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
