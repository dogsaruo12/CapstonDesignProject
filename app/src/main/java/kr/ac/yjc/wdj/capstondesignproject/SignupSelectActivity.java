package kr.ac.yjc.wdj.capstondesignproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SignupSelectActivity extends AppCompatActivity implements View.OnClickListener{

    Button professor, student;
    ActionBar pageActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_select);


        // Actionbar
        pageActionBar = getSupportActionBar();
        //pageActionBar.setIcon();                    // logo
        pageActionBar.setDisplayHomeAsUpEnabled(true);
        pageActionBar.setHomeButtonEnabled(true);
        pageActionBar.setTitle("");
        pageActionBar.setElevation(0);
        /* ================================================ */


        professor = (Button)findViewById(R.id.professor);
        professor.setOnClickListener(this);

        student = (Button)findViewById(R.id.student);
        student.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // 교수 버튼 터치 시
        if (v.getId() == R.id.professor) {

            Intent intent = new Intent(this, ProfSignupActivity.class);
            startActivity(intent);

        // 학생 버튼 터치 시
        } else if (v.getId() == R.id.student) {

            Intent intent = new Intent(this, StudentSignup_1.class);
            startActivity(intent);

        }
    }


    // Actionbar implement function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            this.finish();

            return true;
/*      // 아이콘 클릭 시 홈 화면으로 이동

        } else if (item.getItemId() == android.R.id.home) {

            if () {         // 로그인이 되어 있으면 MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {        // 로그인 되어 있지 않으면 StartActivity
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
            }

            return true;
*/

        } else {

            return super.onOptionsItemSelected(item);

        }
    }
}
