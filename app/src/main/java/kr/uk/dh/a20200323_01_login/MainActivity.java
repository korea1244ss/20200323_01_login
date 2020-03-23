package kr.uk.dh.a20200323_01_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import kr.uk.dh.a20200323_01_login.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding biding = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    @Override
    public void setupEvets() {

    }

    @Override
    public void setValues() {

    }
}
