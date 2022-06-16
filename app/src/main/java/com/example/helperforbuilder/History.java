package com.example.helperforbuilder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class History extends MainActivity {

    public ArrayList<Save> saves = new ArrayList<Save>();
    protected SavesAdapter adapter;
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.list);
        adapter = new SavesAdapter(this, saves);
        recyclerView.setAdapter(adapter);

            for (int i = 0; i < getSize(); i++) {
                String title = mySavesSP.getString(APP_PREFERENCES_TITLE + i, "");
                String text = mySavesSP.getString(APP_PREFERENCES_TEXT + i, "");
                saves.add(new Save(title, text));
            }
            adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.itemHistory);
        return super.onPrepareOptionsMenu(menu);
    }
}

