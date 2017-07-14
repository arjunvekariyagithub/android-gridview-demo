package com.mercari.mercaritest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mercari.mercaritest.data.model.Item;
import com.mercari.mercaritest.R;
import com.mercari.mercaritest.utils.Constants;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by arjun on 7/12/17.
 * <p>
 * Adapter for {@link RecyclerView}. Uses data-binding apis for binding data to
 * {@link RecyclerView} items.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductInfoBindingHolder> {
    private static final String TAG = ProductListAdapter.class.getSimpleName();
    private List<Item> productsList;
    private Context context;

    public ProductListAdapter(List<Item> products, Context context) {
        this.productsList = products;
        this.context = context;
    }

    @Override
    public ProductListAdapter.ProductInfoBindingHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ProductInfoBindingHolder(view);
    }

    /**
     * Called for binding data to particular list item.
     *
     * @param holder   view holder for list item
     * @param position position of list item to which data is to be bound
     */

    @Override
    public void onBindViewHolder(ProductInfoBindingHolder holder, final int position) {
        Item item = productsList.get(position);
        loadImage(holder.getPhotoView(), item.photo);
        holder.getPriceView().setText(String.format(context.getString(R.string.price), item.price));
        holder.getNameView().setText(item.name);
        setSoldIconVisibility(holder.getSoldIcon(), item.status);
    }

    public void setSoldIconVisibility(ImageView soldIcon, String status) {
        if (status.equals(Constants.TEXT_SOLD_OUT)) {
            soldIcon.setVisibility(View.VISIBLE);
        } else {
            soldIcon.setVisibility(View.INVISIBLE);
        }
    }

    public void loadImage(final ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }

        Glide.with(context).load(url)
                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return productsList == null ? 0 : productsList.size();
    }


    /**
     * View holder class for {@link RecyclerView} items.
     */

    public static class ProductInfoBindingHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public ProductInfoBindingHolder(View v) {
            super(v);
            itemView = v;
        }

        public ImageView getPhotoView() {
            return (ImageView) itemView.findViewById(R.id.photo);
        }

        public ImageView getSoldIcon() {
            return (ImageView) itemView.findViewById(R.id.icon_sold);
        }

        public TextView getPriceView() {
            return (TextView) itemView.findViewById(R.id.text_price);
        }

        public TextView getNameView() {
            return (TextView) itemView.findViewById(R.id.text_name);
        }
    }
}