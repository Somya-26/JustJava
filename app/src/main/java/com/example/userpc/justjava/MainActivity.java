package com.example.userpc.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=2;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateTopping = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean checked = whippedCream.isChecked();
        boolean chocolateChecked = chocolateTopping.isChecked();
        EditText editTextView = (EditText) findViewById(R.id.edit_text_view);
        String name = editTextView.getText().toString();

        int price = calculatePrice(quantity, checked, chocolateChecked);
        String message=createOrderSummary(price, checked, chocolateChecked, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }
    private  String createOrderSummary(int price,boolean checked,boolean chocolateChecked,String name)
    {
        String message = "Name: " + name + "\nAdd whipped cream?" + checked +  "\nAdd chocolate topping?" + chocolateChecked + "\nQuantity: " + quantity + "\nTotal: $" + price +"\nThank you!";
        return message;
    }
    public  void incQuantity(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this, "You can't have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);

    }
    public  void decQuantity(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this,"You can't have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
            quantity = quantity - 1;
        display(quantity);
    }
    private int calculatePrice(int quantity,boolean checked,boolean chocolateChecked)
    {
         int price = 10;
        if(checked)
            price+=1;
        if(chocolateChecked)
            price+=2;
        price*=quantity;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /*
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */


}