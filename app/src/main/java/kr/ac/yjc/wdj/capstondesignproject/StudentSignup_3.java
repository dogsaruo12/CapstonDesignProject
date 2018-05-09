package kr.ac.yjc.wdj.capstondesignproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StudentSignup_3 extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    EditText userId, userPw, userPw_chk;
    String sId, sPw, sPw_chk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup_3);

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);

        userId = (EditText) findViewById(R.id.id);
        userPw = (EditText) findViewById(R.id.pw);
        userPw_chk = (EditText) findViewById(R.id.checkpw);

    }


    @Override
    public void onClick(View view) {

        sId = userId.getText().toString();
        sPw = userPw.getText().toString();
        sPw_chk = userPw_chk.getText().toString();


        if (sPw.equals(sPw_chk)) {


        } else {

            userPw_chk.setText("");
            Toast.makeText(this, "비번 확인 다시", Toast.LENGTH_SHORT).show();

        }

    }
}
