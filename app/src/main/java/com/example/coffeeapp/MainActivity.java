package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declare global variables
    int noOfCoffee = 0;
    int priceOfCoffee = 5;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //create a new method for Order button
    public void submitOrder(View v){
        //create objects of TextView
        //TextView priceTextView = findViewById(R.id.price_text_view);
        //calculate price
        int totalCost = noOfCoffee*priceOfCoffee;
        //display price
        //String message = "Total: $"+totalCost+"\n"+"Thank You!";
        //priceTextView.setText(message);

        //Store user name in a string
        EditText userName = findViewById(R.id.userName);
        String name = userName.getText().toString();

      //call the method display to display price

        CheckBox whippedCreamCheckbox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        //call the calculateTotalPrice Method
        int cost = calculatedTotalPrice(hasWhippedCream, hasChocolate);
        String strCost = Integer.toString(cost);

        //create message string
        String message = "Name: " + name+ "\n"+
                "Add whipped cream? " +hasWhippedCream+"\n"+
                "Add chocolate? " +hasChocolate+"\n"+
                "Quantity:" +noOfCoffee+"\n"+
                "Total: $"+cost+"\n"+
                "Thank you!";

        //create a new Explicit Intent to pass the message
        Intent intent = new Intent(this,DisplayOrderDetails.class);
        intent.putExtra("name", name);
        intent.putExtra("sendMessage",message);
        intent.putExtra("cost", strCost);
        startActivity(intent);
    }

    //method to calculate total price of coffee
    public int calculatedTotalPrice(boolean wccb, boolean ccb){
        if (wccb==true){
            priceOfCoffee = priceOfCoffee + 1;
        }

        if (ccb==true){
            priceOfCoffee = priceOfCoffee + 2;
        }
        int totalCost = priceOfCoffee * noOfCoffee;
        return totalCost;
    }

    //method to display
    public void display(int number){
        //create objects of the TextView
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+noOfCoffee);
    }

    //declare a method to increase the no of coffee
    public void increment(View v){
        noOfCoffee = noOfCoffee+1;
        if (noOfCoffee >= 10){
            //reset
            noOfCoffee = 10;
        }
        display(noOfCoffee);
    }

    //declare a method to decrease the no of coffee
    public void decrement(View v){
        noOfCoffee = noOfCoffee-1;
        if (noOfCoffee <= 0){
            //reset
            noOfCoffee = 0;
        }
        display(noOfCoffee);
    }

}
