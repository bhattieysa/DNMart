package com.example.dn_mart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{
    private List<AfterShopListModel> list_datas;
    private Context context;
    private FragmentManager fragmentManager;

    UserInfo userInfo;


    public WishlistAdapter(List<AfterShopListModel> list_datas, Context context, FragmentManager fragmentManager,UserInfo userInfo) {
        this.list_datas = list_datas;
        this.context = context;
        this.fragmentManager=fragmentManager;
        this.userInfo=userInfo;
    }

    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist,parent,false);
        return new WishlistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishlistAdapter.ViewHolder holder, int position) {
        AfterShopListModel listData=list_datas.get(position);


        //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();


        Glide.with(context)
                .load(listData.getImage())
                .into(holder.img);


        holder.name.setText(listData.getName());
        holder.price.setText(listData.getPrice());

        holder.Name=listData.getName();
        holder.Des=listData.getDescription();
        holder.Discount=listData.getDiscount();
        holder.Quantity=listData.getQuantity();
        holder.Image=listData.getImage();
        holder.Price=listData.getPrice();
        holder.ID=listData.getId();

        final String User=userInfo.getKeyId();


        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                        Utils.ADDCARTURL , new Response.Listener <String> () {


                    @Override
                    public void onResponse(String response) {
                        //Log.i("tagconvertstr", "["+response+"]");

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");


                            String Error = jObj.getString("error1");


                            if (!error) {




                                Toast.makeText ( context,"add successfull",Toast.LENGTH_SHORT ).show ();





                            } else {
                                // Error in login. Get the error message
                                if (Error.equals("TRUE1")) {




                                    Toast.makeText ( context,"This Product Already Exist",Toast.LENGTH_SHORT ).show ();





                                }else {

                                    Toast.makeText(context, "failed to add", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {

                            // JSON error
                            e.printStackTrace();


                            Toast.makeText (context,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                        }



                    }
                } , new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Log.e(TAG, "Login Error: " + error.getMessage());

                        Toast.makeText (context,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {


                        // Posting parameters to login url


                        Map<String, String> params = new HashMap<>();

                        params.put("product_id", holder.ID);
                        params.put("user_id", User);




                        return params;


                    }

                };

                // Adding request to request queue
                Singleton.getInstance( context ).addToRequestQueue(stringRequest);










            }
        });





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
//
//
//
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//            }
//        } );




    }

    @Override
    public int getItemCount() {
        return list_datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,price;
        Button cart;
        String Image,Name,Discount,Quantity,Des,Price,ID;




        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            cart=itemView.findViewById(R.id.cart);
            price=itemView.findViewById(R.id.price);


        }
    }
}










