package com.agrovamp.agrovamp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productNameTextView;
    private TextView productPriceTextView;
    private ImageView productImageView;

    private Button addToCartButton;
    private Button addToWishlistButton;
    private Button buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productNameTextView = findViewById(R.id.productNameTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        productImageView = findViewById(R.id.productImageView);

        addToCartButton = findViewById(R.id.addToCartButton);
        addToWishlistButton = findViewById(R.id.addToWishListButton);
        buyNowButton = findViewById(R.id.buyNowButton);

        Intent intent = getIntent();

        if (intent != null) {
            Product product = intent.getParcelableExtra(ProductAdapter.EXTRA_PRODUCT);
            productNameTextView.setText(product.getName());
            productPriceTextView.setText("Rs. " + product.getPrice() + "/-");
            productImageView.setImageDrawable(getImageDrawable(product.getImageName()));
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Drawable getImageDrawable(String name) {
        String uri = "@drawable/" + name;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        return getResources().getDrawable(imageResource);
    }
}
