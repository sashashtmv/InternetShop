package com.entersnowman.internetshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.entersnowman.internetshop.R;
import com.entersnowman.internetshop.adapter.BasketAdapter;
import com.entersnowman.internetshop.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketActivity extends AppCompatActivity {
    DatabaseReference mDatabaseBasket,mDatabaseProducts;
    FirebaseAuth mAuth;
    ArrayList<Product> basket_products;
    BasketAdapter basketAdapter;
    @BindView(R.id.basket_list)
    RecyclerView basketList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.your_basket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        basket_products = new ArrayList<>();
        basketAdapter = new BasketAdapter(basket_products,this);
        basketList.setAdapter(basketAdapter);
        basketList.setLayoutManager(new LinearLayoutManager(this));
        mDatabaseProducts = FirebaseDatabase.getInstance().getReference().child("products");
        mDatabaseBasket = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).child("basket");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mDatabaseBasket.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (final DataSnapshot d: dataSnapshot.getChildren()){
                    mDatabaseProducts.child(d.getKey().split("_")[0]).child(d.getKey().split("_")[1]).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot d2) {
                            Product p = d2.getValue(Product.class);
                            p.setCategory(d.getKey().split("_")[0]);
                            basket_products.add(p);
                            basketAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}