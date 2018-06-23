package com.example.sandigates.uassandioktarian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sandigates.uassandioktarian.adapter.ListSMSDetailAdapter;
import com.example.sandigates.uassandioktarian.adapter.ListSMSRecieveAdapter;
import com.example.sandigates.uassandioktarian.database.TableSmsAdapter;
import com.example.sandigates.uassandioktarian.database.Tablesms;

import java.util.ArrayList;
import java.util.List;

public class DetailReadSMS extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static ListSMSDetailAdapter listAdapter;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_read_sms);
        recyclerView = findViewById(R.id.recycleRead);
        number = getIntent().getStringExtra("phone");
        showList();
    }

    public void showList(){
        List<Tablesms> smsList = new ArrayList<>();
        TableSmsAdapter adapter = new TableSmsAdapter(DetailReadSMS.this);
        smsList = adapter.getDataCondition("number", number);
        listAdapter = new ListSMSDetailAdapter(DetailReadSMS.this, smsList);
        LinearLayoutManager layoutParams = new LinearLayoutManager(DetailReadSMS.this);
        recyclerView.setLayoutManager(layoutParams);
        recyclerView.setAdapter(listAdapter);
    }
}
