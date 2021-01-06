package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    EditText editName,editPhoneNo;
    TextView txtDetailPrice,txtDetailName,txtDetailDescription,txtQuantity;
    ImageView imgDetail;
    Button btnDetailOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editName = findViewById(R.id.editName);
        editPhoneNo = findViewById(R.id.editPhoneNo);
        txtDetailPrice = findViewById(R.id.txtDetailPrice);
        txtDetailName = findViewById(R.id.txtDetailName);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        imgDetail = findViewById(R.id.imgDetail);
        btnDetailOrder = findViewById(R.id.btnDetailOrder);

        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type",0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");

            imgDetail.setImageResource(image);
            txtDetailName.setText(name);
            txtDetailDescription.setText(description);
            txtDetailPrice.setText(String.format("%d", price));


            btnDetailOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            editName.getText().toString(),
                            editPhoneNo.getText().toString(),
                            price, image,
                            Integer.parseInt(txtQuantity.getText().toString()),
                            description, name
                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(DetailActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                }
            });
        }

        else
        {
            final int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();

            final int image = cursor.getInt(4);
            imgDetail.setImageResource(image);
            txtDetailPrice.setText(String.format("%d",cursor.getInt(3)));
            txtDetailName.setText(cursor.getString(7));
            txtDetailDescription.setText(cursor.getString(6));

            editName.setText(cursor.getString(1));
            editPhoneNo.setText(cursor.getString(2));

            btnDetailOrder.setText("Update Now");
            btnDetailOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   boolean isUpdated = helper.updateOrder(
                            editName.getText().toString(),
                            editPhoneNo.getText().toString(),
                            Integer.parseInt(txtDetailPrice.getText().toString()),
                            image,1,
                            txtDetailDescription.getText().toString(),
                            txtDetailName.getText().toString(),
                            id
                    );

                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Updated Success", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(DetailActivity.this, "Not Updated Success", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}


