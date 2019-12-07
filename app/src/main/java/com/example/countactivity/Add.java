package com.example.countactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.save_btn);

        final EditText priceText = (EditText) findViewById(R.id.price_edittext);
        final EditText nameText = (EditText)findViewById(R.id.name_edittext);
        nameText.setText(getIntent().getStringExtra("name"));
        priceText.setText(getIntent().getStringExtra("price"));
        final String time1 = getIntent().getStringExtra("time");


        dbHelper = new DatabaseHelper(this, "Record.db", null, 2);
        Log.d("123123", "onCreate: success");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                String price = priceText.getText().toString();
                String name = nameText.getText().toString();

                values.put("name", name);
                values.put("price", price);

                if(TextUtils.isEmpty(time1)) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());
                    String time = format.format(curDate);
                    values.put("time", time);
                    Log.d("shishia", "onCreate: " + time);
                    db.insert("Record", null, values);
                }
                else{
                    db.update("Record", values, "time = ?", new String[]{time1});
                    Log.d("time246", "onClick: "+time1);
                }


                //Toast.makeText(Add.this,"保存成功",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent();
                //intent.putExtra("name", name);
                //intent.putExtra("price", price);
                //setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
