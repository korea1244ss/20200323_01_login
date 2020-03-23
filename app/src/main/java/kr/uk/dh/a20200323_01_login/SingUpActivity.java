package kr.uk.dh.a20200323_01_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.uk.dh.a20200323_01_login.databinding.ActivitySingUpBinding;
import kr.uk.dh.a20200323_01_login.utils.ServerUtil;

public class SingUpActivity extends BaseActivity {

    ActivitySingUpBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_sing_up);

      setupEvets();
      setValues();
    }

    @Override
    public void setupEvets() {
        binding.singUpEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ID / 비번 / 이름 / 폰번을 따와서 => 서버로 전달
//                회원가입 API에 전달
                String inputId = binding.idEdt.getText().toString();
                String inputPw = binding.pwEdt.getText().toString();
                String inputName = binding.mainEdt.getText().toString();
                String inputPhone = binding.phoneEdt.getText().toString();

                ServerUtil.putRequestSignUp(mContext, inputId, inputPw, inputName, inputPhone, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("회원가입", json.toString());

                        try {
                            int code = json.getInt("code");
                            if (code == 200){

                            }
                            else {
                               final String message = json.getString("message");
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

    }
}
