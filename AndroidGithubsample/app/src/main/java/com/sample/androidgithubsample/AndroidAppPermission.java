package com.sample.androidgithubsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by KRV6User on 12/7/2015.
 */
public class AndroidAppPermission extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    Button btncheckpermission;
    Button btnrequestpermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.androidapppermission);
        btncheckpermission = (Button) this.findViewById(R.id.btncheckpermission);
        btnrequestpermission = (Button) this.findViewById(R.id.btnrequestpermission);
        btncheckpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
        btnrequestpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermission()) {
                    requestPermission();
                } else {

                }
            }
        });
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AndroidAppPermission.this, "Permission granted", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(AndroidAppPermission.this, "Permission not granted", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(AndroidAppPermission.this, "Permission Granted, Now you can access location data.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AndroidAppPermission.this, "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
