package com.example.tuyen;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Handler mHandler;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.getData().getString("COMMAND")) {
//                    case "MODULE_5":
//                        Log.v("Chia het cho 2", msg.getData().getInt("A") + "");
//                        break;
                    case "MODULE_6":
                        Log.v("có bao nhiêu số chẵn", msg.getData().getInt("B") + "");
                        break;
//                    case "MODULE_7":
//                        Log.v("Không chia hết cho 2", msg.getData().getInt("C") + "");
//                        break;
                    case "MODULE_8":
                        Log.v("có bao nhiêu số lẻ", msg.getData().getInt("D") + "");
                        break;
                }
            }
        };


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chan();
            }
        });
    }

    public  void soNguyen(){
        int n = 100, number;
        Random rd = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>(n);

        for (int i = 0; i <= n; i++) {
            number = rd.nextInt(100);
            arrayList.add(number);

        }
        Log.d("array", String.valueOf(arrayList));
    }

    void chan(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 100, number, demchan =0, demle = 0;
                Random rd = new Random();
                ArrayList<Integer> arrayList = new ArrayList<>(n);
                for (int i=0; i <= n; i++){
                    number = rd.nextInt(100);
                    arrayList.add(number);
                    if(number % 2 == 0){
                        demchan++;
//                        Message msg = mHandler.obtainMessage();
//                        Bundle b = new Bundle();
//                        b.putString("COMMAND", "MODULE_5");
//                        b.putInt("A", number);
//                        msg.setData(b);
//                        mHandler.sendMessage(msg);
                    }
                    if (number % 2 != 0){
                        demle++;
                    }
                }
                Log.d("array", String.valueOf(arrayList));
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("COMMAND", "MODULE_6");
                b.putInt("B", demchan);
                msg.setData(b);
                mHandler.sendMessage(msg);

                Message msg1 = mHandler.obtainMessage();
                Bundle b1 = new Bundle();
                b1.putString("COMMAND", "MODULE_8");
                b1.putInt("D", demle);
                msg1.setData(b1);
                mHandler.sendMessage(msg1);
            }
        });
        t.start();
//        soNguyen();

    }

//    void le(){
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int n = 100, number, dem =0;
//                Random rd = new Random();
//                ArrayList<Integer> arrayList = new ArrayList<>(n);
//                for (int i=0; i <= n; i++){
//                    number = rd.nextInt(100);
//                    arrayList.add(number);
//                    if(number % 2 != 0){
//                        dem++;
////                        Message msg = mHandler.obtainMessage();
////                        Bundle b = new Bundle();
////                        b.putString("COMMAND", "MODULE_7");
////                        b.putInt("C", number);
////                        msg.setData(b);
////                        mHandler.sendMessage(msg);
//                    }
//                }
////                Message msg = mHandler.obtainMessage();
////                Bundle b = new Bundle();
////                b.putString("COMMAND", "MODULE_8");
////                b.putInt("D", dem);
////                msg.setData(b);
////                mHandler.sendMessage(msg);
//            }
//        });
//        t.start();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

// viet chuong trinh thuc hien cac cong viec sau:
//   sinh ra ngau nhien 1 mang so nguyen 100 phan tu trong thread main
//   tao 2 thread worker:
//      - dem mang a co bao nhieu so chan (thread chan)
//      - dem mang a co bao nhieu so le (thread le)
//   tao thread tổng, tính tổng các số chia hết cho 5 của mảng a được gửi sang từ 2 thread le và chan