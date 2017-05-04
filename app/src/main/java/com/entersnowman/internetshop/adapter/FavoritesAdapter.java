package com.entersnowman.internetshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.entersnowman.internetshop.ProductActivity;
import com.entersnowman.internetshop.R;
import com.entersnowman.internetshop.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Valentin on 02.05.2017.
 */

public class FavoritesAdapter  extends RecyclerView.Adapter<FavoritesAdapter.ProductHolder> {
    ArrayList<Product> products;
    Context context;
    @Override
    public FavoritesAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_favorites_item, parent, false);
        FavoritesAdapter.ProductHolder vh = new FavoritesAdapter.ProductHolder(v);
        return vh;
    }

    public FavoritesAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ProductHolder holder, int position) {
        ImageLoader imageLoader = ImageLoader.getInstance(); // Получили экземпляр
        imageLoader.init(ImageLoaderConfiguration.createDefault(context)); // Проинициализировали конфигом по умолчанию
        imageLoader.displayImage(products.get(position).getPhoto_url(), holder.productPhoto); // Запустили асинхронный показ картинки
        holder.productName_tv.setText(products.get(position).getName());
        holder.productPrice_tv.setText(Float.toString(products.get(position).getPrice())+" UAH");
        if (products.get(position).isAvailable())
            holder.productAvailability.setText(context.getString(R.string.available));
        else
            holder.productAvailability.setText(context.getString(R.string.unavailable));
        holder.productCategory_tv.setText(products.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView productName_tv;
        TextView productCategory_tv;
        TextView productPrice_tv;
        ImageView productPhoto;
        ImageView addToBasketbutton;
        TextView productAvailability;
        public ProductHolder(View itemView) {
            super(itemView);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("category",category);
                    intent.putExtra("product_id",products.get(getAdapterPosition()).getId());
                    intent.putExtra("product_name",products.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }
            });*/
            productCategory_tv = (TextView) itemView.findViewById(R.id.product_category);
            productName_tv = (TextView) itemView.findViewById(R.id.product_name);
            productPrice_tv = (TextView) itemView.findViewById(R.id.product_price);
            productPhoto = (ImageView) itemView.findViewById(R.id.product_photo);
            productAvailability = (TextView) itemView.findViewById(R.id.product_availability);
            addToBasketbutton = (ImageView) itemView.findViewById(R.id.add_to_basket_button);
        }
    }
}