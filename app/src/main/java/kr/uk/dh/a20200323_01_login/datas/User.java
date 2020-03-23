package kr.uk.dh.a20200323_01_login.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String loginId;
    private String name;
    private String phoneNum;
    private String meme;

    public static User getUserFromJson(JSONObject object){
        User user = new User();

        try {
            user.id = object.getInt("id");
            user.loginId = object.getString("loginName");
            user.name = object.getString("name");
            user.phoneNum = object.getString("phoneNum");
            user.meme = object.getString("meme");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;

    }

    public User(int id, String loginId, String name, String phoneNum, String meme) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.phoneNum = phoneNum;
        this.meme = meme;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }



    public User() {
    }
}
