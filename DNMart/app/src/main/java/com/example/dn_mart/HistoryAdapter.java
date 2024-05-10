package com.example.dn_mart;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<HistoryModel> list_datas;
    private Context context;

    public HistoryAdapter(List<HistoryModel> list_datas, Context context) {
        this.list_datas = list_datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        HistoryModel listData=list_datas.get(position);


        //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();


        holder.Name=listData.getName();
        holder.Price=listData.getPrice();
        holder.Status=listData.getStatus();
        holder.Date=listData.getDate();


        holder.name.setText(holder.Name);
        holder.price.setText(holder.Price);
        holder.status.setText(holder.Status);
        holder.date.setText(holder.Date);



//        holder.Name=listData.getName();
//        holder.O_id=listData.getDes();
//        holder.Discount=listData.getDiscount();
//        holder.Quantity=listData.getQuantity();
//        holder.Image=listData.getImage();
//        holder.Price=listData.getPrice();
//        holder.ID=listData.getID();
//        holder.Flag=listData.getFlag();
//
//        float a= Integer.parseInt(holder.Price);
//        float  b= Integer.parseInt(holder.Discount);
//
//        //holder.percentage.setText(String.valueOf(b));
//
//
//        float d= b/a;
//
//        float n=d*100;
//
//        int f=(int) n ;
//
//
//
//        //Toast.makeText(context,String.valueOf(f),Toast.LENGTH_LONG).show();
//
//
//        int aa= Integer.parseInt(holder.Price);
//        int  bb= Integer.parseInt(holder.Discount);
//        int dicountPrice=aa-bb;
//        holder.discount.setText(String.valueOf(dicountPrice));
//        String value=String.valueOf(f)+"%";
//        holder.percentage.setText(value);
//        holder.price.setBackgroundResource(R.drawable.strike_through);
//        holder.itemView.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ViewProducts.class);
//                intent.putExtra("image", holder.Image);
//                intent.putExtra("name", holder.Name);
//                intent.putExtra("discount", holder.Discount);
//                intent.putExtra("des", holder.Des);
//                intent.putExtra("quantity", holder.Quantity);
//                intent.putExtra("price", holder.Price);
//                intent.putExtra("id", holder.ID);
//                intent.putExtra("flag", holder.Flag);
//                context.startActivity(intent);
//
//
////                    Bundle bundle=new Bundle (  );
////                    bundle.putString ( "name",holder.namee );
////                    bundle.putString ( "bloodgroup",holder.bloodgroupp );
////                    bundle.putString ( "phonenumber",holder.phonenumberr );
////                    bundle.putString ( "city",holder.city);
////                    bundle.putString ( "clg_uni_name",holder.clg_uni_name );
////                    bundle.putString ( "address",holder.address);
////                    bundle.putString ( "donation_date",holder.donation_date);
////                    bundle.putString ( "d_id",holder.d_id );
////                    bundle.putString ( "user_id",holder.user_id );
////                    bundle.putString ( "age",holder.Age );
//            }
//        } );
//
//
//


    }

    @Override
    public int getItemCount() {
        return list_datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,status,price,date;
        String Status,Name,Price,Date,O_id;



        public ViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.Total_Bill);
            status=itemView.findViewById(R.id.status);
            date=itemView.findViewById(R.id.date);

        }
    }
}




