package com.huynhquocmanh.youtubesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.huynhquocmanh.youtubesearch.Model.Item;
import com.huynhquocmanh.youtubesearch.Model.ModelData;
import com.huynhquocmanh.youtubesearch.service.DataAPI;
import com.huynhquocmanh.youtubesearch.service.RetrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String API_KEY = "AIzaSyCGRsTeKpXw89plhwXNFvkUCzrNzm8jw78";
    EditText edtsearch;
    Button btnsearch;
    ListView listView;
    YoutubeAdapter youtubeAdapter;
    ArrayList<Item> mangitem;
    public static String idvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tukhoa = edtsearch.getText().toString();
                tukhoa =tukhoa.replace(" ","%20");
                Docdulieu(tukhoa);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,playvideoActivity.class);
                startActivity(intent);
                 idvideo = mangitem.get(position).getId().getVideoId();
            }
        });


    }

    public void Docdulieu(String tukhoa) {
        DataAPI dataAPI = RetrofitAPI.getdata();
        Call<ModelData> callback = dataAPI.getResurt("snippet", tukhoa, "50", "video", "AIzaSyCGRsTeKpXw89plhwXNFvkUCzrNzm8jw78");
        callback.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {
                ModelData modelData = response.body();
                mangitem = (ArrayList<Item>) modelData.getItems();
                youtubeAdapter = new YoutubeAdapter(MainActivity.this, android.R.layout.simple_list_item_1, mangitem);
                listView.setAdapter(youtubeAdapter);
                Log.d("bbb", modelData.getItems().get(0).getSnippet().getTitle());
            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {

            }
        });
    }

    private void Anhxa() {
        edtsearch = findViewById(R.id.edittextSearch);
        btnsearch = findViewById(R.id.buttonSearch);
        listView = findViewById(R.id.listview);
    }

}
