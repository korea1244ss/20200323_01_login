package kr.uk.dh.a20200323_01_login;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext = this;

    public abstract void setupEvets();
    public abstract void setValues();
}
