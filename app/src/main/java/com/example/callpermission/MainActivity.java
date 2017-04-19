package com.example.callpermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_MAKE_A_CALL =100;
    private final int REQUEST_TAKE_PICTURE = 200;
    protected ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = (ImageView) findViewById(R.id.iv_photo);
    }
    public void Call(View view) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_MAKE_A_CALL);
    }

    public void ChangePicture(View view) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PICTURE);
    }
    public void onRequestPermissionResult(int requestCode, String permission[],int[] Result)
    {
        switch (requestCode)
        {
            case REQUEST_MAKE_A_CALL :
            {
                if (Result.length >0 && Result[0]== PackageManager.PERMISSION_GRANTED)
                    makePhoneCall();
                else
                    System.err.println("Access denied");
                return;
            }
            case REQUEST_TAKE_PICTURE :
            {
                if (Result.length >0 && Result[0]== PackageManager.PERMISSION_GRANTED)
                    takePicture();
                else
                    System.err.println("Access denied");
                return;
            }


        }

    }

    private void takePicture() {
        Intent takephoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Bitmap phooto = (Bitmap) takephoto.getExtras().get("takephoto");
        photo.setImageBitmap(phooto);
    }


    public void makePhoneCall() {
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel: 0988976241"));
        startActivity(call);
    }

}
