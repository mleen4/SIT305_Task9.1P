package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAdvert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);


        CheckBox LostCheckBox = findViewById(R.id.lostCheckBox);
        CheckBox FoundCheckBox = findViewById(R.id.foundCheckBox);



        EditText AdvertName = findViewById(R.id.advertName);
        EditText AdvertPhone = findViewById(R.id.advertPhone);
        EditText AdvertDescription = findViewById(R.id.advertDescription);
        EditText AdvertDate = findViewById(R.id.advertDate);
        EditText AdvertLocation = findViewById(R.id.advertLocation);

        Button saveButton = findViewById(R.id.saveAdvertButton);

        DatabaseHelper db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = AdvertName.getText().toString();
                Integer phone = Integer.parseInt(AdvertPhone.getText().toString());
                String description = AdvertDescription.getText().toString();
                String date = AdvertDate.getText().toString();
                String location = AdvertLocation.getText().toString();






                if(LostCheckBox.isChecked() && FoundCheckBox.isChecked() || !FoundCheckBox.isChecked() && !LostCheckBox.isChecked())
                {
                    Toast.makeText(CreateAdvert.this, "Please Select Either Lost or Found", Toast.LENGTH_SHORT).show();
                }
                else if (LostCheckBox.isChecked())
                {
                    Item item = new Item(name,phone,description,date,location, true);
                    long result = db.insertItem(item);
                    if(result > 0);
                    {
                        Toast.makeText(CreateAdvert.this, "Added!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (FoundCheckBox.isChecked())
                {




                    Boolean result = db.foundItem(name);
                    if(result)
                    {
                        Toast.makeText(CreateAdvert.this, "You Found the Item! Thanks!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!result)
                    {
                        Toast.makeText(CreateAdvert.this, "Item wasn't in our DB!", Toast.LENGTH_SHORT).show();
                    }


                }








            }
        });



    }
}