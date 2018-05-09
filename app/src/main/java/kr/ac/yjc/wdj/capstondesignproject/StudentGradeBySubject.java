package kr.ac.yjc.wdj.capstondesignproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentGradeBySubject extends Activity{


    private String TAG = StudentGradeBySubject.class.getSimpleName();


    TextView subjectName, season;
    TableLayout tableLayout_1, tableLayout_2;
    TableRow addTableRow;
    Button showDetailBtn_2, showSimplyBtn_2;

    ArrayList<HashMap<String, String>> contactList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade_by_subject);

        showDetailBtn_2 = findViewById(R.id.showDetailBtn_2);
        showSimplyBtn_2 = findViewById(R.id.showSimplyBtn_2);

        subjectName     = findViewById(R.id.subjectName);
        season          = findViewById(R.id.season);

        tableLayout_1   = findViewById(R.id.tableLayout_1);
        tableLayout_2   = findViewById(R.id.tableLayout_2);

        contactList = new ArrayList<>();

        new GetContacts().execute();
    }






    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(StudentGradeBySubject.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
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

                        // 배열로 시험 분류 리스트가 반환 될 거임
                        // JSON array -> JSON object로 바꿔야됨

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




        // 테이블에 값 입력

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);






        }
    }







    public void showDetail_2 (View view) {

        showDetailBtn_2.setVisibility(View.GONE);
        showSimplyBtn_2.setVisibility(View.VISIBLE);


        // TableRow 추가


    }



    public void showSimply_2 (View view) {

        showSimplyBtn_2.setVisibility(View.GONE);
        showDetailBtn_2.setVisibility(View.VISIBLE);


        // TableRow 삭제 or GONE


    }



}