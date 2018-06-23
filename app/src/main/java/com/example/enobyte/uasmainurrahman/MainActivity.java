package com.example.enobyte.uasmainurrahman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.enobyte.uasmainurrahman.adapter.ListSMSRecieveAdapter;
import com.example.enobyte.uasmainurrahman.database.TableSmsAdapter;
import com.example.enobyte.uasmainurrahman.database.Tablesms;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static RecyclerView recycleRecive;
    private static MainActivity inst;
    private static ListSMSRecieveAdapter listAdapter;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleRecive = findViewById(R.id.recycleRecive);
        refreshLayout = findViewById(R.id.swipe);
        new DatabaseManager(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SendSMS.class));
            }
        });


        showList();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                showList();
            }
        });

    }

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

    public void showList() {
        List<Tablesms> smsList = new ArrayList<>();
        TableSmsAdapter adapter = new TableSmsAdapter(MainActivity.this);
        smsList = adapter.getListChat2();
        listAdapter = new ListSMSRecieveAdapter(MainActivity.this, smsList);
        LinearLayoutManager layoutParams = new LinearLayoutManager(MainActivity.this);
        recycleRecive.setLayoutManager(layoutParams);
        recycleRecive.setAdapter(listAdapter);
        refreshLayout.setRefreshing(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        showList();
    }



}
