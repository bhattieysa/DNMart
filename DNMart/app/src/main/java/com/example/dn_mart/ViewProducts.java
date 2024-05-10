package com.example.dn_mart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.dn_mart.prefs.UserInfo;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class ViewProducts extends AppCompatActivity {

    ImageView image,back;
    TextView name,quantity,price,des,discount;
    UserInfo userInfo;
    Button cart;
    LikeButton heart;
    JSONObject jObj1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        heart=findViewById(R.id.heart_button);
        image=findViewById(R.id.image);
        name=findViewById(R.id.name);
        quantity=findViewById(R.id.quantity);
        price=findViewById(R.id.price);
        des=findViewById(R.id.des);
        discount=findViewById(R.id.discount);
        back=findViewById(R.id.back);
        cart=findViewById(R.id.cart);
        userInfo=new UserInfo(ViewProducts.this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();


        String Image = intent.getStringExtra("image");
        final String FLAG = intent.getStringExtra("flag");
        String Name = intent.getStringExtra("name");
        String Price = intent.getStringExtra("price");
        String Discount = intent.getStringExtra("discount");
        String  Quantity = intent.getStringExtra("quantity");
        String Des = intent.getStringExtra("des");
        final String ID = intent.getStringExtra("id");
        final String u_id = userInfo.getKeyId();

        heart.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {


                StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                        Utils.WISHLIST_URL, new Response.Listener <String> () {


                    @Override
                    public void onResponse(String response) {
                        Log.i("Login Response: ", "["+response+"]");

                        try {
                            jObj1 = new JSONObject(response);
                            Boolean error = jObj1.getBoolean("error");
//                            String error1 = jObj.getString("error1");


                            if (!error) {



//
//                                Intent intent=new Intent(Checkout.this,Checkout1.class);
//                                intent.putExtra("name", Name);
//                                intent.putExtra("address",Address);
//                                intent.putExtra("number",Number);
//
//                                startActivity(intent);

//                                if(error1.equals("yes")){
                                    Toast.makeText(ViewProducts.this,"Allready entered",Toast.LENGTH_SHORT).show();

//                                }else {

                                    Toast.makeText(ViewProducts.this,"sucessfully entered",Toast.LENGTH_SHORT).show();


//                                }








                            } else {
                                // Error in login. Get the error message


                                Toast.makeText ( ViewProducts.this,"Faild to enterd to wishlist",Toast.LENGTH_SHORT ).show ();

                            }
                        } catch (JSONException e) {

                            // JSON error
                            e.printStackTrace();

                            Log.e(TAG, "Login Error: " + e.getMessage());
                            Toast.makeText (ViewProducts.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                        }



                    }
                } , new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {




                        Toast.makeText (ViewProducts.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {


                        // Posting parameters to login url


                        Map<String, String> params = new HashMap<>();

                        params.put("u_id", u_id);
                        params.put("p_id",ID);


                        return params;


                    }

                };

                // Adding request to request queue
                Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);



            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });



        name.setText(Name);

        price.setText(Price);
        discount.setText(Discount);

        quantity.setText(Quantity);
        des.setText(Des);

        Glide.with(ViewProducts.this)
                .load(Image)
                .into(image);





        cart.setOnClickListener(new View.OnClickListener() {
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




                                Toast.makeText ( ViewProducts.this,"add successfull",Toast.LENGTH_SHORT ).show ();

                                if(FLAG.equals("HOME")){

                                    Intent intent1=new Intent(ViewProducts.this,Home.class);
                                    startActivity(intent1);


                                }







                            } else {
                                // Error in login. Get the error message
                                if (Error.equals("TRUE1")) {


                                    Toast.makeText ( ViewProducts.this,"This Product Already Exist",Toast.LENGTH_SHORT ).show ();


                                }else {

                                    Toast.makeText(ViewProducts.this, "failed to add", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {

                            // JSON error
                            e.printStackTrace();


                            Toast.makeText (ViewProducts.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                        }



                    }
                } , new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Log.e(TAG, "Login Error: " + error.getMessage());

                        Toast.makeText (ViewProducts.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {


                        // Posting parameters to login url


                        Map<String, String> params = new HashMap<>();

                        params.put("product_id", ID);
                        params.put("user_id", u_id  );




                        return params;


                    }

                };

                // Adding request to request queue
                Singleton.getInstance( ViewProducts.this ).addToRequestQueue(stringRequest);




            }
        });






    }







}

