package kr.ac.yjc.wdj.capstondesignproject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class StudentGradeManagementList extends AppCompatActivity{

    private String TAG = StudentGradeManagementList.class.getSimpleName();
    private ListView lv;
    private View lists;

    private int checkedYear, checkedSeason;

    ArrayList<HashMap<String, String>> contactList;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int season, int dayOfMonth){
            Log.d("SeasonPickerDialog", "year = " + year + ", season = " + season + ", day = " + dayOfMonth);

            checkedYear = year;
            checkedSeason = season;


            // 지정된 값을 서버로 다시 전송하여










            // ListView를 재구성
            new GetContacts().execute();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade_management);


        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);


        SimpleDateFormat forYear = new SimpleDateFormat("yyyy");
        String getYear = forYear.format(new Date(System.currentTimeMillis()));
        checkedYear = Integer.getInteger(getYear);


        SimpleDateFormat forMonth = new SimpleDateFormat("mm");
        String getMonth = forMonth.format(new Date(System.currentTimeMillis()));
        int currentMonth = Integer.getInteger(getMonth);


        if (currentMonth >= 3 || currentMonth <= 8) {
            checkedSeason = 1;
        } else if (currentMonth > 8){
            checkedSeason = 2;
        } else if (currentMonth < 3) {
            checkedYear = checkedYear - 1;
            checkedSeason = 2;
        }


        new GetContacts().execute();
    }




    public void selectSeasonBtn(View view) {

        SeasonPickerDialog pd = new SeasonPickerDialog();
        pd.setListener(d);
        pd.show(getSupportFragmentManager(), "SeasonPickerDialog");

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(StudentGradeManagementList.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            // 서버로 전송 한 뒤 data를 받아





            // 우선 출력하고,

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "";    // URL 입력란
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    // 여기서 서버에서 json으로 오는 값 parsing

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String list_image = c.getString("image");       // 서버에서 오는 값대로 input 값 고쳐야됨
                        String subject = c.getString("subject");        // 마찬가지
                        String professor = c.getString("professor");    // 마찬가지

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("list_image", list_image);
                        contact.put("subject", subject);
                        contact.put("professor", professor);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }



        /*
            email -> list_image
            mobile -> subject
            blank -> professor
        */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(StudentGradeManagementList.this, contactList,
                    R.layout.list_row, new String[]{ "list_image","subject","professor"},
                    new int[]{R.id.list_image, R.id.subject, R.id.professor});


            lists = getLayoutInflater().inflate(R.layout.list_row, null, false);
            RelativeLayout clickableLayout = lists.findViewById(R.id.lists);
            clickableLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(StudentGradeManagementList.this, StudentGradeBySubject.class);
                    intent.putExtra("subject", lists.findViewById(R.id.subject).toString());
                    intent.putExtra("year", checkedYear);
                    intent.putExtra("season", checkedSeason);
                    startActivity(intent);

                }
            });

            lv.setAdapter(adapter);
        }
    }

}