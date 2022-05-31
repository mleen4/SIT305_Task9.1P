package com.example.task71p;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class CreateAdvert extends AppCompatActivity {

    private static final String TAG = "running";
    public double latitude;
    public double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);


        CheckBox LostCheckBox = findViewById(R.id.lostCheckBox);
        CheckBox FoundCheckBox = findViewById(R.id.foundCheckBox);


        Places.initialize(getApplicationContext(), "APIKEY");

        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                Toast.makeText(CreateAdvert.this, "Place: " + place.getName(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });




        EditText AdvertName = findViewById(R.id.advertName);
        EditText AdvertPhone = findViewById(R.id.advertPhone);
        EditText AdvertDescription = findViewById(R.id.advertDescription);
        EditText AdvertDate = findViewById(R.id.advertDate);

        Button saveButton = findViewById(R.id.saveAdvertButton);

        DatabaseHelper db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = AdvertName.getText().toString();
                Integer phone = Integer.parseInt(AdvertPhone.getText().toString());
                String description = AdvertDescription.getText().toString();
                String date = AdvertDate.getText().toString();






                if(LostCheckBox.isChecked() && FoundCheckBox.isChecked() || !FoundCheckBox.isChecked() && !LostCheckBox.isChecked())
                {
                    Toast.makeText(CreateAdvert.this, "Please Select Either Lost or Found", Toast.LENGTH_SHORT).show();
                }
                else if (LostCheckBox.isChecked())
                {
                    Item item = new Item(name,phone,description,date, latitude, longitude, true);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
