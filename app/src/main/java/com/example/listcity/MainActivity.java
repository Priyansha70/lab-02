package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText newCity;

    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Montreal"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // --- ADD / CONFIRM ---
        final Button addButton = findViewById(R.id.add_button);
        final Button confirmButton = findViewById(R.id.confirm_button);
        final Button deleteButton = findViewById(R.id.remove_button);

        newCity = findViewById(R.id.new_city);

        addButton.setOnClickListener(v -> {
            confirmButton.setVisibility(View.VISIBLE);
            newCity.setVisibility(View.VISIBLE);
        });

        confirmButton.setOnClickListener(v -> {
            String cityName = newCity.getText().toString().trim();

            if (cityName.isEmpty()) {
                Toast.makeText(this, "City name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.add(cityName);
            cityAdapter.notifyDataSetChanged();

            newCity.setText("");
            confirmButton.setVisibility(View.INVISIBLE);
            newCity.setVisibility(View.INVISIBLE);
        });

        //  Select a city by tapping it
        cityList.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedIndex = i;
            Toast.makeText(this, "Selected: " + dataList.get(i), Toast.LENGTH_SHORT).show();
        });

        // DELETE CITY button removes the selected city
        deleteButton.setOnClickListener(v -> {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Tap a city to select it first", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();

            selectedIndex = -1;
        });
    }
}
