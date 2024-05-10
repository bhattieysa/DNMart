package com.example.dn_mart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Checkout2 extends AppCompatActivity {

    TextView name,bill,address,number;
    String Name,Bill,Address,Number,U_id,Total;
    Button confirm;
    ProgressDialog progressBar;
    UserInfo userInfo;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_checkout2);

        String nameS = getIntent().getStringExtra("name");
        String addressS = getIntent().getStringExtra("address");
        String numberS = getIntent().getStringExtra("number");
        final String o_id = getIntent().getStringExtra("o_id");
         Total = getIntent().getStringExtra("total");


        confirm=findViewById(R.id.confirm);
        name=findViewById(R.id.name);
        back=findViewById(R.id.back);
        bill=findViewById(R.id.bill);
        address=findViewById(R.id.address);
        number=findViewById(R.id.number);
        userInfo=new UserInfo(this);
        progressBar= new ProgressDialog(Checkout2.this);

        name.setText(nameS);
        address.setText(addressS);
        number.setText(numberS);
        bill.setText(Total);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name=name.getText().toString().trim();
                Bill=bill.getText().toString().trim();
                Address=address.getText().toString().trim();
                Number=number.getText().toString().trim();
                U_id=userInfo.getKeyId();
                progressBar.show();
                progressBar.setMessage("Loading...");


                StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                        Utils.Confirm_URL , new Response.Listener <String> () {


                    @Override
                    public void onResponse(String response) {
                        Log.i("tagconvertstr", "["+response+"]");

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");


                            if (!error) {
                                progressBar.dismiss();




                                Toast.makeText ( Checkout2.this,"Order Confirm Successful",Toast.LENGTH_SHORT ).show ();
                                Intent intent= new Intent(Checkout2.this,Home.class);
                                startActivity(intent);
                                finish();





                            } else {
                                // Error in login. Get the error message
                                progressBar.hide();

                                Toast.makeText ( Checkout2.this,"Order Confirm UnSuccessful",Toast.LENGTH_SHORT ).show ();

                            }
                        } catch (JSONException e) {
                            progressBar.hide();
                            // JSON error
                            e.printStackTrace();


                            Toast.makeText (Checkout2.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                        }



                    }
                } , new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.hide();
                        //Log.e(TAG, "Login Error: " + error.getMessage());

                        Toast.makeText (Checkout2.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {


                        // Posting parameters to login url


                        Map<String, String> params = new HashMap<>();

                        params.put("name", Name);
                        params.put("number", Number);
                        params.put("bill", Bill);
                        params.put("address", Address);
                        params.put("u_id", U_id);
                        params.put("o_id", o_id);
                        params.put("total", Total);




                        return params;


                    }

                };

                // Adding request to request queue
                Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);









            }
        });







    }




}

