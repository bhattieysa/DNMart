package com.example.dn_mart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dn_mart.prefs.UserInfo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BarcodeReader extends AppCompatActivity {
    ProgressDialog progressDialog;
    UserInfo userInfo;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_barcode_reader);
        userInfo=new UserInfo(this);


                Scannow();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Reasult Not Found", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BarcodeReader.this, Home.class));
                finish();


            } else {

                final String barcode = result.getContents();
                user_id=userInfo.getKeyId();
                //Toast.makeText(this,barcode,Toast.LENGTH_LONG).show();







                StringRequest strReq = new StringRequest(Request.Method.POST,

                        Utils.BARCODE_URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("ahmar", "Login Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            boolean error1 = jObj.getBoolean("error1");


                        if (error) {
                            // Error in login. Get the error message

                            // Toast.makeText(BarcodeReader.this,"Failed To Scan Product",Toast.LENGTH_LONG).show();
                            progressDialog.hide();
                        } else {
                            if(error1){

                                Toast.makeText(BarcodeReader.this,"Product Already Exist",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(BarcodeReader.this, Home.class));
                                finish();

                            }else {


                                progressDialog.dismiss();


                                startActivity(new Intent(BarcodeReader.this, Home.class));
                                finish();
                            }

                        }

                        } catch (JSONException e) {
                            // JSON error

                            e.printStackTrace();
                            progressDialog.hide();
                            Toast.makeText(BarcodeReader.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(BarcodeReader.this,"Unknown Error",Toast.LENGTH_SHORT).show();
                        progressDialog.hide();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<>();
                        params.put("u_id",user_id );
                        params.put("barcode_id",barcode );

                        return params;
                    }

                };

                // Adding request to request queue
                Singleton.getInstance (this).addToRequestQueue ( strReq );








            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void Scannow(){


        IntentIntegrator integrator=new IntentIntegrator(BarcodeReader.this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(true);

        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);


        integrator.setPrompt("Scan Product Barcode in DN_MART");
        integrator.setCameraId(0);
// Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();







    }
}
