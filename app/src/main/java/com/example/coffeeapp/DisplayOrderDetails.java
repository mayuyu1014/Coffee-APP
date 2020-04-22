package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOrderDetails extends AppCompatActivity {

    Intent intent;
    String message;
    String name;
    String totalCost;
    CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new CoffeeDBHandler(this, null, null, 1);
        setContentView(R.layout.activity_display_order_details);

        //catch the message that is being sent and display the details
        intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("sendMessage");
        totalCost = intent.getStringExtra("cost");

        //display the message in this activity
        TextView displayText = findViewById(R.id.displayText);
        displayText.setText(message);
    }

    //button to save data in SQLite database
    public void addButtonClicked(View view){
        int intTotalCost = Integer.parseInt(totalCost);
        Order order = new Order(name,intTotalCost);
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "Data Saved!nae", Toast.LENGTH_SHORT).show();
    }

    //method to display the Email
    public void emailOrder(View view){
        intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order" + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void salesReport(View view){
        //read the details from the database to produce the report
        String dbString = dbHandler.databaseToString();
        //start the new intent here
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }

}
