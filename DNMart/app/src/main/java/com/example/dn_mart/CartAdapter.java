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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
        private List<CartModel> list_datas;
        private Context context;
        private FragmentManager fragmentManager;
    JSONArray jsonArray;
    JSONObject server_responce;
    RequestQueue mQueue;
    UserInfo userInfo;
    int pos;





        public CartAdapter(List<CartModel> list_datas, Context context, FragmentManager fragmentManager,UserInfo userInfo,int pos) {
           this.list_datas = list_datas;
            this.context = context;
            this.fragmentManager=fragmentManager;
            this.userInfo=userInfo;
            this.pos =pos;
        }


        @NonNull
        @Override
        public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycleviewlist,parent,false);
            return new CartAdapter.ViewHolder(view);
        }


    @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            CartModel listData=list_datas.get(position);


            //Toast.makeText(context,listData.getImage()+"has",Toast.LENGTH_LONG).show();


            Glide.with(context)
                    .load(listData.getImage())
                    .into(holder.img);


            holder.name.setText(listData.getName());
            holder.price.setText(listData.getPrice());


            holder.Name=listData.getName();
            holder.Des=listData.getDescription();
            holder.Discount=listData.getDiscount();

            holder.Quantity=Integer.valueOf(listData.getQuantity());

            holder.Image=listData.getImage();
            holder.Price=listData.getPrice();
            holder.ID=listData.getId();
            holder.itemView.setTag(holder.ID);


            holder.Counter1= String.valueOf(listData.getcartquantity());
        holder.Counter= Integer.valueOf(holder.Counter1);


        holder.quantity.setText(String.valueOf(holder.Counter));


            //Toast.makeText(context,""+holder.Counter,Toast.LENGTH_LONG).show();

            final String User=userInfo.getKeyId();
            mQueue = Volley.newRequestQueue(context);

            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (holder.Counter < holder.Quantity) {

                        holder.Counter = holder.Counter + 1;


                        holder.quantity.setText(String.valueOf(holder.Counter));

                        if (holder.Counter >= holder.Quantity) {

                            holder.Counter = holder.Quantity;


                        }


                        String URL = Utils.UPDATECART_URL + "?id=" + holder.ID + "&quantity=" + holder.Counter + "&user_id=" + User;


                        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d(TAG, "Login Response: " + response.toString());


                                        try {


                                            jsonArray = response.getJSONArray("products");


                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                server_responce = jsonArray.getJSONObject(i);


                                                String error = server_responce.getString("error");

                                                if (error.equals("true")) {


                                                    Toast.makeText(context, "Internet Error", Toast.LENGTH_LONG).show();


                                                } else {


                                                    holder.quantity.setText(String.valueOf(holder.Counter));

                                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                    CartFragment cartFragment = new CartFragment();

                                                    fragmentTransaction.replace(R.id.nav_host_fragment, cartFragment);
                                                    fragmentTransaction.addToBackStack(null);
                                                    fragmentTransaction.commit();


                                                }

                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();

                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }


                                    }


                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                        mQueue.add(request);


                    }
                }
            });

            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (holder.Counter == 1) {

                        holder.Counter = 1;
                        //holder.quantity.setText(holder.Counter);


                    } else {


                        holder.Counter = holder.Counter - 1;
                        holder.quantity.setText(String.valueOf(holder.Counter));


                        String URL = Utils.UPDATECART_URL + "?id=" + holder.ID + "&quantity=" + holder.Counter + "&user_id=" + User;


                        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d(TAG, "Login Response: " + response.toString());


                                        try {


                                            jsonArray = response.getJSONArray("products");


                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                server_responce = jsonArray.getJSONObject(i);


                                                String error = server_responce.getString("error");

                                                if (error.equals("true")) {


                                                    Toast.makeText(context, "Internet Error", Toast.LENGTH_LONG).show();


                                                } else {


                                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                    CartFragment cartFragment = new CartFragment();

                                                    fragmentTransaction.replace(R.id.nav_host_fragment, cartFragment);
                                                    fragmentTransaction.addToBackStack(null);
                                                    fragmentTransaction.commit();


                                                }


                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();

                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }


                                    }


                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                        mQueue.add(request);

                    }
             





                }








            });






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



                    context.startActivity(intent);


                }
            } );



        }

        @Override
        public int getItemCount() {
            return list_datas.size();
        }



    public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView img;
            private TextView name,price,quantity;
            Button plus,minus;
            String Image,Name,Discount,Des,Price,ID,Counter1;
            int Counter, Quantity;




            public ViewHolder(View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.image);
                name=itemView.findViewById(R.id.name);
                plus=itemView.findViewById(R.id.plus);
                minus=itemView.findViewById(R.id.minus);
                quantity=itemView.findViewById(R.id.quantity);
                price=itemView.findViewById(R.id.price);


            }
        }
    }













