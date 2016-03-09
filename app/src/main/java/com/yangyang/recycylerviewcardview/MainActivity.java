package com.yangyang.recycylerviewcardview;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<News> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add1:
                ma.add(1);
                break;
            case R.id.delete1:
                ma.delete(1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private MyAdapter ma;
    private static String Url = "http://10.151.208.83:8080/JsonProject2/JsonAction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.swap_list);
        MyAsyncTask task = new MyAsyncTask();
        task.execute(Url);


    }

    class MyAsyncTask extends AsyncTask<String, Void, List<News>> {
        @Override
        protected void onPostExecute(List<News> newses) {
            super.onPostExecute(newses);
            ma = new MyAdapter(MainActivity.this, newses);
            ma.msetOnClickListener(new MyAdapter.mClickListenner() {
                @Override
                public void mOnItemClick(View v, int pos) {
                    Toast.makeText(MainActivity.this,"click"+pos,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void mOnItemLongClick(View v, int pos) {
                    Toast.makeText(MainActivity.this,"longclick"+pos,Toast.LENGTH_SHORT).show();

                }
            });
           //设置布局管理器，可以将其设置成网格，瀑布流等多种形式。
//            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            //设置适配器
            recyclerView.setAdapter(ma);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<News> doInBackground(String... params) {
            return getJsonData(params[0]);
        }
    }

    private List<News> getJsonData(String Url) {
        List<News> mlist = new ArrayList<>();
        News news;
        try {
            String jsonString=readStream(new URL(Url).openStream());
            JSONObject jsonObject;
            Log.d("xys", jsonString);

            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("persons");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    news = new News();
                    news.setIconUrl(jsonObject.getString("iconUrl"));//这里重写一遍
                    news.setContent(jsonObject.getString("content"));
                    news.setTitle(jsonObject.getString("title"));
                    mlist.add(news);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    private String readStream(InputStream is){
        InputStreamReader isr;
        String result="";
        try {
            String line="";
            isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            try {
                while((line=br.readLine())!=null){
                    result+=line;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;

    }

}
