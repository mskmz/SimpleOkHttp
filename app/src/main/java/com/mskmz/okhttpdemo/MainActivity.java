package com.mskmz.okhttpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
  //>>>>>>>>>>>>>>>DEBUG配置>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  private static final String TAG = "MainActivity>>>";
  private static final boolean DEBUG = true;
  //<<<<<<<<<<<<<<<DEBUG配置<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  String PATH = "http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  //使用Okhttp获取数据
  //首先 根据要求 实现构建者模式
  //然后实现Call机制
  public void getData() throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .build();
    final Request request = new Request.Builder().url(PATH).build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
        
      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (DEBUG) Log.d(TAG, "onFailure: " + response);
      }
    });

  }
}
