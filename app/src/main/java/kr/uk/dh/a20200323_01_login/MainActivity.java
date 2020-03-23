package kr.uk.dh.a20200323_01_login;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import org.json.JSONObject;

import kr.uk.dh.a20200323_01_login.databinding.ActivityMainBinding;
import kr.uk.dh.a20200323_01_login.utils.ContextUtil;
import kr.uk.dh.a20200323_01_login.utils.ServerUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding biding = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvets();
        setValues();
    }

    @Override
    public void setupEvets() {

//        체크박스에 체크가 될떄 (변화가 있을때) 마다
//        체크 여부 저장.

        biding.idCheckBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("처크여부",isChecked+"");
                //                ContextUtil을 이용해서, 체크 여부를 저장.
                ContextUtil.setIdCheck(mContext, isChecked);
            }
        });


//        로그인 버튼을 누르면 = > 입력되어있는 이메일 저장.
    biding.loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (biding.idCheckBtn.isChecked()){
//                체크가 되어 있는 상황
                String inputEmail = biding.emailEdt.getText().toString();

                ContextUtil.setEmail(mContext, inputEmail);

            }
            else {
                ContextUtil.setEmail(mContext, " ");
            }
               String inputEmail = biding.emailEdt.getText().toString();
               String inputPw = biding.pwEdt.getText().toString();


            ServerUtil.postRequestLogin(mContext, inputEmail, inputPw, new ServerUtil.JsonResponseHandler() {
                @Override
                public void onResponse(JSONObject json) {

                    Log.d("JSON내용-메인에서", json.toString());
                }
            });

        }
    });

    }

    @Override
    public void setValues() {



        biding.emailEdt.setText(ContextUtil.getEmail(mContext));
//        이 화면을 키면, 저장된 이메일 값을 화면에 입력.
    biding.idCheckBtn.setChecked(ContextUtil.isIdCheck(mContext));
    }
}
