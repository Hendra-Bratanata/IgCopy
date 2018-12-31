package com.ichirotech.igcopy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    PostAdapter postAdapter;
    JadwalAdapter jadwalAdapter;

    @BindView(R.id.rvCatagory)
    RecyclerView rv;
    @BindView(R.id.rvCatagoryMain)
    RecyclerView rv2;
    private TextView mTextMessage;

    ArrayList<StatusItem> list = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        String uri = "https://animeyou.net/api/jadwal.php";
        postAdapter = new PostAdapter(this);
        jadwalAdapter = new JadwalAdapter(this);


        RequestJadwal requestJadwal = new RequestJadwal();
        requestJadwal.execute(uri);
    }

    private class RequestJadwal extends AsyncTask<String,Void,ArrayList<Jadwal>>{

        @Override
        protected ArrayList<Jadwal> doInBackground(String... strings) {
            String url = strings[0];
            final ArrayList<Jadwal> listJadwal = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    try {
                        String hasil = new String(responseBody);
                        JSONObject obj = new JSONObject(hasil);
                        JSONArray jsonArray = obj.getJSONArray("data");

                        for (int i = 0; i<jsonArray.length();i++){
                            JSONObject jadwalObj = jsonArray.getJSONObject(i);
                            Jadwal jadwal = new Jadwal(jadwalObj);
                            listJadwal.add(jadwal);

                        }


                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            return listJadwal;
        }

        @Override
        protected void onPostExecute(ArrayList<Jadwal> jadwals) {
            super.onPostExecute(jadwals);
            jadwalAdapter.setListJadwal(jadwals);
            postAdapter.setListPost(jadwals);
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayout.HORIZONTAL,false));
            rv.setAdapter(jadwalAdapter);
            rv2.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rv2.setAdapter(postAdapter);
        }
    }


}
