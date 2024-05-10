package com.example.dn_mart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


    public class ShopAdapter extends RecyclerView.Adapter<com.example.dn_mart.ShopAdapter.ViewHolder>{
        private List<ShopModel> list_datas;
        private androidx.fragment.app.FragmentManager fragmentManager;
        private Context context;

        public ShopAdapter(List<ShopModel> list_datas, Context context ,androidx.fragment.app.FragmentManager fragmentManager) {
            this.list_datas = list_datas;
            this.fragmentManager = fragmentManager;
            this.context = context;
        }

        @NonNull
        @Override
        public com.example.dn_mart.ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shoprecycleviewlist,parent,false);
            return new com.example.dn_mart.ShopAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final com.example.dn_mart.ShopAdapter.ViewHolder holder, int position) {
            ShopModel listData=list_datas.get(position);


            //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();
            Log.d("Eysa",listData.getImage());

            Glide.with(context)
                    .load(listData.getImage())
                    .into(holder.img);


            holder.name.setText(listData.getName());

            holder.ID=listData.getId();
            holder.Name=listData.getName();





            holder.itemView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {




                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();



                    Bundle bundle=new Bundle (  );
                    bundle.putString ( "id",holder.ID );
                    bundle.putString ( "name",holder.Name);




                    AfterShopList afterShopList=new AfterShopList();
                    afterShopList.setArguments ( bundle );
                    fragmentTransaction.replace(R.id.nav_host_fragment,afterShopList);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();








                }
            } );





        }

        @Override
        public int getItemCount() {
            return list_datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView img;
            private TextView name;
            String Image,Name,ID;



            public ViewHolder(View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.image);
                name=itemView.findViewById(R.id.name);

        }
    }





}
