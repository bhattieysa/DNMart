package com.example.dn_mart;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{
    private List<InvoiceModel> list_datas;
    private Context context;

    public InvoiceAdapter(List<InvoiceModel> list_datas, Context context) {
        this.list_datas = list_datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewinvoice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        InvoiceModel listData=list_datas.get(position);


        //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();




        holder.product_name.setText(listData.getProduct_name());
        holder.price.setText(listData.getPrice());
        holder.quantity.setText(listData.getQuantity());
        holder.amount.setText(listData.getAmount());

//        holder.Name=listData.getName();
//        holder.Des=listData.getDes();
//        holder.Discount=listData.getDiscount();
//        holder.Quantity=listData.getQuantity();
//        holder.Image=listData.getImage();
//        holder.Price=listData.getPrice();
//        holder.ID=listData.getID();
//        holder.Flag=listData.getFlag();
//
//
//        holder.itemView.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(context, ViewProducts.class);
//
//
//                intent.putExtra("image", holder.Image);
//                intent.putExtra("name", holder.Name);
//                intent.putExtra("discount", holder.Discount);
//                intent.putExtra("des", holder.Des);
//                intent.putExtra("quantity", holder.Quantity);
//                intent.putExtra("price", holder.Price);
//                intent.putExtra("id", holder.ID);
//                intent.putExtra("flag", holder.Flag);
//
//
//
//                context.startActivity(intent);
//
//            }
//        } );
//




    }

    @Override
    public int getItemCount() {
        return list_datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView product_name,quantity,price,amount;
        String Product_name,Quantity,Price,Total;



        public ViewHolder(View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.name);
            quantity=itemView.findViewById(R.id.quantity);
            price=itemView.findViewById(R.id.price);
            amount=itemView.findViewById(R.id.amount);
        }
    }
}




