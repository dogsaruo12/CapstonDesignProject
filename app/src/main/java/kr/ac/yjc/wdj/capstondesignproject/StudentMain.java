package kr.ac.yjc.wdj.capstondesignproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;



public class StudentMain extends Activity implements View.OnClickListener {

    private FingerprintAuthenticationDialogFragment mFragment;
    private boolean connectingStatus = false;
    BeaconManager beaconManager;
    RelativeLayout studentManagement, checkInBtn;
    ImageView checkInImg;
    TextView connectingText, checkInTxt;
    Animation translate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        studentManagement = (RelativeLayout) findViewById(R.id.studentManagement);


        checkInImg = (ImageView) findViewById(R.id.checkInImg);
        checkInTxt = (TextView) findViewById(R.id.checkInTxt);


        checkInBtn = (RelativeLayout) findViewById(R.id.checkInBtn);
        checkInBtn.setOnClickListener(this);

        connectingText = (TextView) findViewById(R.id.connectingText);

        translate = AnimationUtils.loadAnimation(this, R.anim.translate_anim);


        // Connecting with Bluetooth
        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            SystemRequirementsChecker.checkWithDefaultDialogs(this);
        }




        connectBeacon("20cae8a0-a9cf-11e3-a5e2-0800200c9a66", 87, 59734);


        mFragment = new FingerprintAuthenticationDialogFragment();


/*

        BroadcastReceiver bcState = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int currentState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);

                switch(currentState) {
                    case BluetoothAdapter.STATE_TURNING_OFF:
                    case BluetoothAdapter.STATE_OFF:

                        Toast.makeText(getApplicationContext(), "Bluetooth Reconnect", Toast.LENGTH_SHORT).show();
                        connectBeacon("20cae8a0-a9cf-11e3-a5e2-0800200c9a66", 87, 59734);

                        break;
                }
            }
        };

        IntentFilter intent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bcState, intent);
*/

    }


    public void changeMode() {


    }


    @Override
    public void onClick(View v) {




        if (v.getId() == R.id.checkInBtn) {

            if (connectingStatus) {   // Check the Connection with Beacon

                // Visualize Fingerprint Popup
                // Cognize User's Fingerprint
                // Send Check-In Data To Server
                // Alert Completed User's Fingerprint

                mFragment.show(this.getFragmentManager(), "my_fragment");

                new Thread() {
                    public void run() {
                        try {
                            sendJsonText();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();


                new JsonLoadingTask().execute();
/*

                GetData task = new GetData();
                Toast.makeText(this, task.execute("http://13.124.213.132/student/hardware/come_school").toString(), Toast.LENGTH_SHORT).show();
*/

/*
                String sMessage = "1301235"; // 보내는 메시지를 받아옴

                SendByHttp(sMessage); // 메시지를 서버에 보냄

                String[][] parsedData = jsonParserList(); // 받은 메시지를 json 파싱

                Toast.makeText(this, parsedData[0][0] , Toast.LENGTH_SHORT).show();
*/




               /* try {

                    DefaultHttpClient httpclient = new DefaultHttpClient();

                    List<Cookie> cookies = httpclient.getCookieStore().getCookies();

                    HttpPost httpost = new HttpPost("http://13.124.213.132/student/hardware/come_school");

                    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                    nvps.add(new BasicNameValuePair("stdId", "8609933"));

                    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

                    HttpResponse response = httpclient.execute(httpost);
                    HttpEntity entity = response.getEntity();

                    System.out.println("Login form get: " + response.getStatusLine());
                    if (entity != null) {
                        entity.consumeContent();
                    }

                    System.out.println("Post logon cookies:");
                    cookies = httpclient.getCookieStore().getCookies();
                    if (cookies.isEmpty()) {
                        System.out.println("None");
                    } else {
                        for (int i = 0; i < cookies.size(); i++) {
                            Toast.makeText(this, "cookie  :  " + cookies.get(i).toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    // When HttpClient instance is no longer needed,
                    // shut down the connection manager to ensure
                    // immediate deallocation of all system resources
                    // httpclient.getConnectionManager().shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh : mm : ss");

                String getTime = sdf.format(date);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("출석 완료");
                builder.setMessage(getTime + "\n출석이 완료되었습니다.");
                builder.setPositiveButton("확인", null);
                builder.create().show();


            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("연결 요청");
                builder.setMessage("블루투스와 비콘에 먼저 연결해주세요");
                builder.setPositiveButton("확인", null);
                builder.create().show();

            }
        }
    }


    // Beacon Connecting Method
    public void connectBeacon(final String uuid, final int major, final int minor) {

        beaconManager = new BeaconManager(getApplicationContext());

        // Connection with Beacon
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {

            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(
                        new Region("monitored region", UUID.fromString(uuid), major, minor)         // Beacon's UUID, Major, Minor key value
                );
            }
        });


        // Alert Text When Beacon was Connected or Disconnected
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {

            // Connected
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                showNotification("Beacon Connected", "WDJ 강의실 비콘에 연결되었습니다.");
                connectingStatus = true;
/*

                connectingText.setVisibility(View.VISIBLE);
                connectingText.startAnimation(translate);
                connectingText.setText("WDJ 강의실 Beacon-1에 연결되었습니다.");
                connectingStatus = true;
*/

            }

            // Disconnected
            @Override
            public void onExitedRegion(Region region) {
                showNotification("Beacon Disconnected", "WDJ 강의실 비콘의 연결이 끊겼습니다.");
                connectingStatus = false;
            }
        });

    }


    public void showNotification(String title, String message) {

        Intent notifyIntent = new Intent(this, StartActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_bluetooth)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();


        notification.defaults |= Notification.DEFAULT_SOUND;


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(1, notification);
        }

    }






    public void sendJsonText() throws IOException, JSONException {

        StringBuilder sb = new StringBuilder();

        String http = "http://13.124.213.132/student/hardware/come_school";


        HttpURLConnection urlConnection=null;
        try {
            URL url = new URL(http);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.connect();

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("stdId", "1301235");
            OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonParam.toString());
            out.flush();
            out.close();

            int HttpResult =urlConnection.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                //Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT);

            }else{
                System.out.println(urlConnection.getResponseMessage());
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
    }






    public String getJsonText() {

        // 내부적으로 문자열 편집이 가능한 StringBuffer 생성자
        StringBuffer sb = new StringBuffer();

        try {
            String line = getStringFromUrl("http://13.124.213.132/student/hardware/come_school");
            // 원격에서 읽어온 데이터로 JSON 객체 생성
            JSONObject object = new JSONObject(line);

            sb.append("status : ").append(object.getString("status"));
            sb.append("message : ").append(object.getString("message"));


            // "kkt_list" 배열로 구성 되어있으므로 JSON 배열생성
            JSONArray Array = new JSONArray(object.getString(""));

            /*for (int i = 0; i < Array.length(); i++) {
                // bodylist 배열안에 내부 JSON 이므로 JSON 내부 객체 생성
                JSONObject insideObject = Array.getJSONObject(i);

                // StringBuffer 메소드 ( append : StringBuffer 인스턴스에 뒤에 덧붙인다. )
                // JSONObject 메소드 ( get.String(), getInt(), getBoolean() .. 등 : 객체로부터 데이터의 타입에 따라 원하는 데이터를 읽는다. )

                sb.append("status : ").append(insideObject.getString("status")).append("\n");
                sb.append("message : ").append(insideObject.getString("message")).append("\n");

            } // for*/
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    } // getJsonText




    // getStringFromUrl : 주어진 URL 페이지를 문자열로 얻는다.
    public String getStringFromUrl(String url) throws UnsupportedEncodingException {

        // 입력스트림을 "UTF-8" 를 사용해서 읽은 후, 라인 단위로 데이터를 읽을 수 있는 BufferedReader 를 생성한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(getInputStreamFromUrl(url), "UTF-8"));

        // 읽은 데이터를 저장한 StringBuffer 를 생성한다.
        StringBuffer sb = new StringBuffer();

        try {
            // 라인 단위로 읽은 데이터를 임시 저장한 문자열 변수 line
            String line = null;

            // 라인 단위로 데이터를 읽어서 StringBuffer 에 저장한다.
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    } // getStringFromUrl



    // getInputStreamFromUrl : 주어진 URL 에 대한 입력 스트림(InputStream)을 얻는다.
    public static InputStream getInputStreamFromUrl(String url) {
        InputStream contentStream = null;
        try {
            // HttpClient 를 사용해서 주어진 URL에 대한 입력 스트림을 얻는다.
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            contentStream = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentStream;
    } // getInputStreamFromUrl



    // AsyncTask 를 이용 UI 처리 및 Background 작업 등을 하나의 클래스에서 작업 할 수 있도록 지원해준다.
    private class JsonLoadingTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strs) {
            return getJsonText();
        } // doInBackground : 백그라운드 작업을 진행한다.
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        } // onPostExecute : 백그라운드 작업이 끝난 후 UI 작업을 진행한다.
    } // JsonLoadingTask









/*    private class GetData extends AsyncTask<String, Void, String> {
        String errorString = null;


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bufferedReader.close();

                return stringBuilder.toString().trim();

            } catch (Exception e) {

                errorString = e.toString();

                return null;
            }

        }
    }*/
}