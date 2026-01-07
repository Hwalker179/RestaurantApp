package com.example.restaurantmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MenuEditor extends AppCompatActivity {

    private SessionManager sessionManager;
    private List<String> menuItemsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editor);

        sessionManager = new SessionManager(this);

        Button backButton = findViewById(R.id.btnBack);
        EditText etNewMenuItem = findViewById(R.id.etNewMenuItem);
        Button btnAddItem = findViewById(R.id.btnAddItem);
        ListView lvCurrentMenu = findViewById(R.id.lvCurrentMenu);

        loadMenuItems();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItemsList);
        lvCurrentMenu.setAdapter(adapter);

        backButton.setOnClickListener(v -> finish());

        btnAddItem.setOnClickListener(v -> {
            String newItem = etNewMenuItem.getText().toString().trim();
            if (!newItem.isEmpty()) {
                if (!menuItemsList.contains(newItem)) {
                    menuItemsList.add(newItem);
                    saveAndRefresh();
                    etNewMenuItem.setText("");
                    Toast.makeText(this, "Added: " + newItem, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Item already exists", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show();
            }
        });

        lvCurrentMenu.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = menuItemsList.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Remove Item")
                    .setMessage("Are you sure you want to remove '" + selectedItem + "'?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        menuItemsList.remove(position);
                        saveAndRefresh();
                        Toast.makeText(this, "Removed: " + selectedItem, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void loadMenuItems() {
        Set<String> itemsSet = sessionManager.getMenuItems();
        menuItemsList = new ArrayList<>(itemsSet);
    }

    private void saveAndRefresh() {
        sessionManager.saveMenuItems(menuItemsList.stream().collect(java.util.stream.Collectors.toSet()));
        adapter.notifyDataSetChanged();
    }
}
