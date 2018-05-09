package kr.ac.yjc.wdj.capstondesignproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class StudentSignup_1 extends AppCompatActivity implements View.OnClickListener {

    Button checkStudentIdBtn, nextBtn;
    RelativeLayout userImgUpload;
    EditText studentId;
    TextView userIdCheckText;
    private Boolean nextBtnClickable = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup_1);

        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);

        studentId = (EditText)findViewById(R.id.studentId);

        userIdCheckText = (TextView) findViewById(R.id.userIdCheckText);

    }

    @Override
    public void onClick(View view) {

        if (nextBtnClickable) {

            Intent intent = new Intent(this, StudentSignup_2.class);
            IntentFilter itf = new IntentFilter();
            intent.putExtra("id", studentId.getText());

            startActivity(intent);
        }

    }



    public void checkOverlap () {
/*
        if () {             // 중복되지 않은 경우

            userIdCheckText.setText("회원가입 가능한 학번입니다.");
            userIdCheckText.setTextColor(0x009688);

            studentId.setClickable(false);
            nextBtnClickable = true;


        } else if () {      // 중복값이 return되어 올 경우

            userIdCheckText.setText("학번이 중복되었습니다.");
            userIdCheckText.setTextColor(0xf4511e);

            studentId.setText("");
            nextBtnClickable = false;

        }*/
    }


    public void setUserImgUpload () {


    }

}
