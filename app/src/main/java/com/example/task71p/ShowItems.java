package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);


        ListView itemsListView = findViewById(R.id.itemListView);

        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<String> itemList = db.fetchAllItems();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        itemsListView.setAdapter(adapter);


        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = (itemList.get(i));

                Intent intent = new Intent(getApplicationContext(), ListViewItem.class);
                intent.putExtra("itemName", name);

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                startActivity(intent);

            }
        });







    }
}