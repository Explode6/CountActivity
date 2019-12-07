package com.example.countactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MoreInfo extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    ArrayList<String> nameAll = new ArrayList<>();
    ArrayList<String> priceAll = new ArrayList<>();
    ArrayList<String> timeAll = new ArrayList<>();
    private List<CountRecord> countRecordList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        dbHelper = new DatabaseHelper(this,"Record.db", null,2);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Record", null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                nameAll.add(cursor.getString(cursor.getColumnIndex("name")));
                priceAll.add(cursor.getString(cursor.getColumnIndex("price")));
                timeAll.add(cursor.getString(cursor.getColumnIndex("time")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        initCount();

        final CountAdapter countAdapter = new CountAdapter(MoreInfo.this, R.layout.count_item, countRecordList);
        ListView listView = (ListView)findViewById(R.id.InfoView);
        listView.setAdapter(countAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CountRecord countRecord = countRecordList.get(position);
                Intent intent = new Intent(MoreInfo.this, Add.class);
                intent.putExtra("name",countRecord.getName());
                intent.putExtra("price",countRecord.getPrice());
                intent.putExtra("time",countRecord.getTime());
                intent.putExtra("position", position);
                Log.d("short", "onItemClick: short");
                startActivityForResult(intent,1);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CountRecord countRecord = countRecordList.get(position);
                db.delete("Record", "time = ?", new String[]{countRecord.getTime()});
                Log.d("long", "onItemClick: long");
                return true;
            }
        });

        Button addMoreBtn = (Button)findViewById(R.id.add_more_btn);
        addMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MoreInfo.this,Add.class);
                startActivity(intent1);
            }
        });
    }

    public void initCount(){
        for(int i=0;i<nameAll.size();i++){
            CountRecord countRecord = new CountRecord(nameAll.get(i),priceAll.get(i),timeAll.get(i));
            countRecordList.add(countRecord);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
