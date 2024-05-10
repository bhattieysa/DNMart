package com.example.dn_mart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.android.volley.VolleyLog.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Checkout extends AppCompatActivity {
        EditText name,adress,city,cnic,number;
        Button next;
        String Name ,Address,City,Cnic,Number,U_id;
        UserInfo userInfo;
        ImageView back;

    LocationManager locationManager;
    LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_checkout);


        name=findViewById(R.id.name);
        adress=findViewById(R.id.address);
        city=findViewById(R.id.city);
        cnic=findViewById(R.id.cnic);
        number=findViewById(R.id.number);
        next=findViewById(R.id.next);
        userInfo=new UserInfo(this);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=name.getText().toString();
                Address=adress.getText().toString();
                City=city.getText().toString();
                Cnic=cnic.getText().toString();
                Number=number.getText().toString();
                U_id=userInfo.getKeyId();


                if(Name.isEmpty()){

                    name.setError("Name Can't Be Blank");
                    name.requestFocus();
                    return;


                }


                if(Address.isEmpty()){

                    adress.setError("Address Can't Be Blank");
                    adress.requestFocus();
                    return;


                }


                if(City.isEmpty()){

                    city.setError("City Can't Be Blank");
                    city.requestFocus();
                    return;


                }


                if(Cnic.isEmpty()){

                    cnic.setError("cnic Can't Be Blank");
                    cnic.requestFocus();
                    return;


                }


                if(Number.isEmpty()){

                    number.setError("number Can't Be Blank");
                    number.requestFocus();
                    return;


                }




















                StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                        Utils.CHECKOUT1, new Response.Listener <String> () {


                    @Override
                    public void onResponse(String response) {
                        Log.i("Login Response: ", "["+response+"]");

                        try {
                            JSONObject jObj = new JSONObject(response);
                            Boolean error = jObj.getBoolean("error");
                            String o_id = jObj.getString("error1");





                            if (!error) {




                                Intent intent=new Intent(Checkout.this,Checkout1.class);
                                intent.putExtra("name", Name);
                                intent.putExtra("address",Address);
                                intent.putExtra("number",Number);
                                intent.putExtra("o_id",o_id);

                                startActivity(intent);







                            } else {
                                // Error in login. Get the error message


                                Toast.makeText ( Checkout.this,"Faild to Next",Toast.LENGTH_SHORT ).show ();

                            }
                        } catch (JSONException e) {

                            // JSON error
                            e.printStackTrace();

                            Log.e(TAG, "Login Error: " + e.getMessage());
                            Toast.makeText (Checkout.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                        }



                    }
                } , new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {




                        Toast.makeText (Checkout.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {


                        // Posting parameters to login url


                        Map<String, String> params = new HashMap<>();

                        params.put("name", Name);
                        params.put("number", Number);
                        params.put("address", Address);
                        params.put("city", City);
                        params.put("cnic", Cnic);
                        params.put("u_id", U_id);



                        return params;


                    }

                };

                // Adding request to request queue
                Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);









            }
        });


    }

   void Location(){


       locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       listener = new LocationListener() {
           @Override
           public void onLocationChanged(Location location) {

               Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
               try {

                   List<android.location.Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                   if(addressList != null && addressList.size() > 0) {
                       if(addressList.get(0).getAddressLine(0) != null) {
                           adress.setText(addressList.get(0).getAddressLine(0));
                       }
                   }

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onStatusChanged(String s, int i, Bundle bundle) {

           }

           @Override
           public void onProviderEnabled(String s) {

           }

           @Override
           public void onProviderDisabled(String s) {

           }
       };
       if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
       }
       else {
           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
       }
   }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(ContextCompat.checkSelfPermission(Checkout.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
                }
            }
        }

    }



    }

