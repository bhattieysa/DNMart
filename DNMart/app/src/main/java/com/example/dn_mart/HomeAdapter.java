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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
        private List<HomeModel> list_datas;
        private Context context;

        public HomeAdapter(List<HomeModel> list_datas, Context context) {
            this.list_datas = list_datas;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.homerecycleviewlist,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            HomeModel listData=list_datas.get(position);


            //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();


            Glide.with(context)
                    .load(listData.getImage())
                    .into(holder.img);


            holder.name.setText(listData.getName());








            holder.price.setText(listData.getPrice());



            holder.Name=listData.getName();
            holder.Des=listData.getDes();
            holder.Discount=listData.getDiscount();
            holder.Quantity=listData.getQuantity();
            holder.Image=listData.getImage();
            holder.Price=listData.getPrice();
            holder.ID=listData.getID();
            holder.Flag=listData.getFlag();

            float a= Integer.parseInt(holder.Price);
            float  b= Integer.parseInt(holder.Discount);

            //holder.percentage.setText(String.valueOf(b));


            float d= b/a;

            float n=d*100;

            int f=(int) n ;



            //Toast.makeText(context,String.valueOf(f),Toast.LENGTH_LONG).show();


            int aa= Integer.parseInt(holder.Price);
            int  bb= Integer.parseInt(holder.Discount);
            int dicountPrice=aa-bb;
            holder.discount.setText(String.valueOf(dicountPrice));
            String value=String.valueOf(f)+"%";
            holder.percentage.setText(value);
            holder.price.setBackgroundResource(R.drawable.strike_through);
            holder.itemView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewProducts.class);
                    intent.putExtra("image", holder.Image);
                    intent.putExtra("name", holder.Name);
                    intent.putExtra("discount", holder.Discount);
                    intent.putExtra("des", holder.Des);
                    intent.putExtra("quantity", holder.Quantity);
                    intent.putExtra("price", holder.Price);
                    intent.putExtra("id", holder.ID);
                    intent.putExtra("flag", holder.Flag);
                    context.startActivity(intent);


//                    Bundle bundle=new Bundle (  );
//                    bundle.putString ( "name",holder.namee );
//                    bundle.putString ( "bloodgroup",holder.bloodgroupp );
//                    bundle.putString ( "phonenumber",holder.phonenumberr );
//                    bundle.putString ( "city",holder.city);
//                    bundle.putString ( "clg_uni_name",holder.clg_uni_name );
//                    bundle.putString ( "address",holder.address);
//                    bundle.putString ( "donation_date",holder.donation_date);
//                    bundle.putString ( "d_id",holder.d_id );
//                    bundle.putString ( "user_id",holder.user_id );
//                    bundle.putString ( "age",holder.Age );
}
            } );





        }

        @Override
        public int getItemCount() {
            return list_datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView img;
           private TextView name,discount,price,percentage;
            String Image,Name,Discount,Quantity,Des,Price,ID,Flag,Percentage;



            public ViewHolder(View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.image);
                name=itemView.findViewById(R.id.name);
                price=itemView.findViewById(R.id.price);
                percentage=itemView.findViewById(R.id.percentage);
                discount=itemView.findViewById(R.id.discount);
            }
        }
    }




