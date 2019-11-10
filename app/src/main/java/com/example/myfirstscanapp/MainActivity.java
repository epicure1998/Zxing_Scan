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
    private Button qr_btn;
    // private TextView textView=(TextView)findViewById(R.id.textView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_btn=(Button)findViewById(R.id.scan_btn);
        qr_btn=(Button)findViewById(R.id.qr_btn);
        final Activity activity=this;
        scan_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //  ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                IntentIntegrator integrator=new IntentIntegrator(activity);integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES );
                integrator.setPrompt("二维码扫描开始");
                integrator.setCameraId(0);//0是使用默认相机
                integrator.setBeepEnabled(true);//扫描后的提示音
                integrator.setBarcodeImageEnabled(false);//？
                integrator.initiateScan();
            }
        });
        qr_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //  ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                IntentIntegrator integrator=new IntentIntegrator(activity);integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES );
                integrator.setPrompt("条形码扫描开始");
                integrator.setCameraId(0);//0是使用默认相机
                integrator.setBeepEnabled(true);//扫描后的提示音
                integrator.setBarcodeImageEnabled(false);//？
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
                Toast.makeText(this,"Successfully scanning"+ result.getContents() , Toast.LENGTH_LONG).show();
                TextView textView=(TextView)findViewById(R.id.textView);
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