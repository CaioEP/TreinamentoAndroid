package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView minhaView = (TextView) findViewById(R.id.textView);
        Log.i(TAG, "onCreate " + minhaView.getText());
        minhaView.setText(getString(R.string.sin));
        Button butao = (Button)findViewById(R.id.butao);
        butao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Oi tudo bem como vai!",Toast.LENGTH_SHORT).show();
                minhaView.setText("Alterei");
            }
        });
    }
}
