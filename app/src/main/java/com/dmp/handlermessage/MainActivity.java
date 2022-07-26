package com.dmp.handlermessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String AZEEM = "azeem";
    TextView textView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            handler = new Handler(getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    String s=  msg.getData().getString(AZEEM);
                    textView.setText(s);
                }
            };

        });

        Runnable runnable = new Runnable() {
            Message message = new Message();
            Bundle bundle = new Bundle();
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    SystemClock.sleep(1000);

                    bundle.putString(AZEEM,"number: "+i);

                }
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };

        new Thread(runnable).start();
    }


}