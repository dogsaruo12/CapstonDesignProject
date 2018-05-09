package kr.ac.yjc.wdj.capstondesignproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by dogsa on 2018-04-17.
 */

public class StudentAttendence extends AppCompatActivity {


    Button showDetailBtn, showSimplyBtn;
    TextView attendenceInfoTxt, tardyInfoTxt, leaveEarlyInfoTxt, missedInfoTxt;
    TableLayout attendencdInfoTable, tardyInfoTable, leaveEarlyInfoTable ,missedInfoTable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendence);


        attendenceInfoTxt   = findViewById(R.id.attendenceInfoTxt);
        tardyInfoTxt        = findViewById(R.id.tardyInfoTxt);
        leaveEarlyInfoTxt   = findViewById(R.id.leaveEarlyInfoTxt);
        missedInfoTxt       = findViewById(R.id.missedInfoTxt);


        attendencdInfoTable = findViewById(R.id.attendenceInfoTable);
        tardyInfoTable      = findViewById(R.id.tardyInfoTable);
        leaveEarlyInfoTable = findViewById(R.id.leaveEarlyInfoTable);
        missedInfoTable     = findViewById(R.id.missedInfoTable);


        showDetailBtn       = findViewById(R.id.showDetailBtn);
        showSimplyBtn       = findViewById(R.id.showSimplyBtn);






    }



    public void showDetail () {

        attendenceInfoTxt.setVisibility(View.VISIBLE);
        tardyInfoTxt.setVisibility(View.VISIBLE);
        leaveEarlyInfoTxt.setVisibility(View.VISIBLE);
        missedInfoTxt.setVisibility(View.VISIBLE);

        attendencdInfoTable.setVisibility(View.VISIBLE);
        tardyInfoTable.setVisibility(View.VISIBLE);
        leaveEarlyInfoTable.setVisibility(View.VISIBLE);
        missedInfoTable.setVisibility(View.VISIBLE);

        showSimplyBtn.setVisibility(View.VISIBLE);
        showDetailBtn.setVisibility(View.GONE);

    }



    public void showSimply () {

        attendenceInfoTxt.setVisibility(View.GONE);
        tardyInfoTxt.setVisibility(View.GONE);
        leaveEarlyInfoTxt.setVisibility(View.GONE);
        missedInfoTxt.setVisibility(View.GONE);

        attendencdInfoTable.setVisibility(View.GONE);
        tardyInfoTable.setVisibility(View.GONE);
        leaveEarlyInfoTable.setVisibility(View.GONE);
        missedInfoTable.setVisibility(View.GONE);

        showSimplyBtn.setVisibility(View.GONE);
        showDetailBtn.setVisibility(View.VISIBLE);

    }




}
