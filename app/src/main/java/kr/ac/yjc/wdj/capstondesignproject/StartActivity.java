package kr.ac.yjc.wdj.capstondesignproject;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class StartActivity extends AppCompatActivity {

    private EditText id, pw;
    private Button loginButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        id = findViewById(R.id.userId);
        pw = findViewById(R.id.userPw);

        radioGroup = findViewById(R.id.radioGroup);
        loginButton = findViewById(R.id.loginButton);

    }



    public void loginStart(View view){

        String userId = id.getText().toString();
        String userPw = pw.getText().toString();

        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(id);
        String type = radioButton.getText().toString();

        String device = "android";

        if(userId.equals("") || userPw.equals("")){
            Toast.makeText(getApplicationContext(), "아이디 / 비밀번호 입력", Toast.LENGTH_SHORT).show();
        }else {
            login(userId, userPw, type, device);
        }
    }



    public void login(String userId, String userPw, String type, String device){


        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                try{
                    JSONObject jsonResponse = new JSONObject(response);

                    boolean success = jsonResponse.getBoolean("status");

                    if(success){
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");


                        //로그인에 성공했으므로 MainActivity로 넘어감
                        Intent intent = new Intent(StartActivity.this, StudentMain.class);
                        intent.putExtra("status", status);
                        intent.putExtra("message", message);
                        StartActivity.this.startActivity(intent);

                    }else{//로그인 실패시
                        AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                        builder.setMessage("Login failed").setNegativeButton("retry", null).create().show();
                    }

                }catch (JSONException  e){
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(userId, userPw, type, device, responseListener);
        RequestQueue queue = Volley.newRequestQueue(StartActivity.this);
        queue.add(loginRequest);
    }


}
