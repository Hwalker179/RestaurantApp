package com.example.restaurantmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MakeReservationActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        sessionManager = new SessionManager(this);

        final EditText nameInput = findViewById(R.id.etName);
        final EditText numOfPeopleInput = findViewById(R.id.etNumOfPeople); // Added this
        final EditText dateInput = findViewById(R.id.etDate);
        final EditText timeInput = findViewById(R.id.etTime);
        Button submitButton = findViewById(R.id.btnSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String numOfPeople = numOfPeopleInput.getText().toString().trim(); // Added this
                String date = dateInput.getText().toString().trim();
                String time = timeInput.getText().toString().trim();

                if (name.isEmpty() || numOfPeople.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(MakeReservationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String reservationDetails = "Booking for " + name + " (" + numOfPeople + " people) on " + date + " at " + time;

                sessionManager.saveReservation(name, date, time);

                Toast.makeText(MakeReservationActivity.this, "Reservation Made!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
