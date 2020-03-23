package kr.uk.dh.a20200323_01_login;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
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
        biding.sigUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                biding.sigUpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,SingUpActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

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
// 응답실행코드는 => 비동기 처리가 반드시 필요함.
// 비동기 : 다른 할 일들을 하다가, 완료 되면 별도로 실행해주자.
// okHTTP : 비동기 처리를 자동으로 지원. => 별도의 쓰레드가 알아서 진행
// => 이 onResponse는 다른 쓰레드가 돌리고 있다.
// -> 다른 쓰레드가 UI를 건드리면 앱이 터짐
                    Log.d("JSON내용-메인에서", json.toString());

                    try {
                       final String message = json.getString("message");
                        Log.d("서버가주는 메세지",message);
                        int code = json.getInt("code");
                        Log.d("서버가 주는 코드값",code+"");

                        if (code == 200) {

                            JSONObject data = json.getJSONObject("data");
                            JSONObject user = data.getJSONObject("user");
                            String  token = data.getString("token");
                           final String name = user.getString("name");
                           final String phone = user.getString("phone");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,String.format("%s / %s ",name,phone), Toast.LENGTH_LONG).show();
                                }
                            });


//                            로그인한 사람의 이름을 토스르로 출력.
//                            해당 기능이 성공적으로 동작
                        }
                        else {
//                            뭔가 문제가 있었다.
//                            Toast를 띄우는데 앱이 죽는다!
//                           조치 : UIThread 안에 Toast 너어둠.
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
