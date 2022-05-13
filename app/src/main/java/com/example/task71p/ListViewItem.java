package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListViewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item);



        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");

        TextView ItemNameView = findViewById(R.id.listItemName);
        TextView ItemDateView = findViewById(R.id.listItemDate);
        TextView ItemLocationView = findViewById(R.id.listItemLocation);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ItemNameView.setText(itemName);

        Cursor response = databaseHelper.queryItem(itemName);

        response.moveToFirst();
        ItemDateView.setText(response.getString(3));
        ItemLocationView.setText(response.getString(4));

        Button removeButton = findViewById(R.id.listItemRemoveButton);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean res = databaseHelper.foundItem(itemName);
                if (res)
                {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }

            }
        });




    }
}