package com.example.mycurrentlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements View.OnTouchListener {
    EditText userName,passWord;
    Button loginButton;
    ImageView img;
    RelativeLayout.LayoutParams layoutParams;
    int height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        initView();

        ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
    }

    private void initView() {
        userName = findViewById(R.id.driver_UN);
        passWord = (EditText) findViewById(R.id.driver_PW);
        loginButton = (Button) findViewById(R.id.loginButton);
        img = (ImageView) findViewById(R.id.imageView);
        loginButton.setOnTouchListener(this);

        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(v.getId() == loginButton.getId()){
            layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

            if(MotionEvent.ACTION_DOWN == event.getAction())
            {
                onBtnDown();
//                Intent i = new Intent(this,LocationTackerService.class);
//                startService(i);
            }
            if(MotionEvent.ACTION_UP == event.getAction()) {
                onBtnUp();
            }
        }
        return true;
    }

    private void onBtnUp() {
        loginButton.setTextSize(20);
        layoutParams.width = width;
        layoutParams.height = height;
        loginButton.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Drawable dr1 = getResources().getDrawable(R.drawable.login_btn2);
            loginButton.setBackground(dr1);
        }

        String UN = userName.getText().toString();
        String PW = passWord.getText().toString();

        Context context = Login_Activity.this;
        Activity activity = Login_Activity.this;

        LoginBackgroundTask loginBackgroundTask = new LoginBackgroundTask(context,activity);
        loginBackgroundTask.execute(UN,PW);
    }

    private void onBtnDown() {
        loginButton.setTextSize(23);
        layoutParams.width = width + 30;
        layoutParams.height = height + 5;
        loginButton.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Drawable dr = getResources().getDrawable(R.drawable.login_btn);
            loginButton.setBackground(dr);
        }

        Vibrator vibrator1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator1.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator1.vibrate(30);
        }
    }
}
