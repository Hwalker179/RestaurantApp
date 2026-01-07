package com.example.restaurantmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private SessionManager sessionManager;
    private ListView menuListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> menuItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sessionManager = new SessionManager(this);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnAddEdit = findViewById(R.id.btnAddEdit);
        menuListView = findViewById(R.id.lvMenu);

        btnBack.setOnClickListener(v -> finish());
        btnLogout.setOnClickListener(v -> {
            Intent logoutIntent = new Intent(Menu.this, MainActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logoutIntent);
        });

        String userType = getIntent().getStringExtra("user_type");
        if (userType != null && userType.equals("staff")) {
            btnAddEdit.setVisibility(View.VISIBLE);
            btnAddEdit.setOnClickListener(v -> {
                Intent editorIntent = new Intent(Menu.this, MenuEditor.class);
                startActivity(editorIntent);
            });
        } else {
            btnAddEdit.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMenuItems();
    }

    private void loadMenuItems() {
        menuItemsList = new ArrayList<>(sessionManager.getMenuItems());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItemsList);

        menuListView.setAdapter(adapter);
    }
}
