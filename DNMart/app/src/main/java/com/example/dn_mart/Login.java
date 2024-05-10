package com.example.dn_mart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dn_mart.prefs.UserInfo;
import com.example.dn_mart.prefs.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    EditText email,password;

    ProgressDialog progressDialog;
    Button login;
    TextView Signup;

    String Email,Password;
    UserInfo userInfo;
    UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        Signup=findViewById(R.id.signup_no);
        progressDialog=new ProgressDialog(this);
        password=findViewById(R.id.password);
        userInfo=new UserInfo(this);
        userSession=new UserSession(this);



        if(userSession.isUserLoggedin()){
            startActivity(new Intent(this, Home.class));
            finish();
        }

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(Login.this, signup.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Email=email.getText().toString();
                Password=password.getText().toString();

                login(Email,Password);




            }
        });
    }



    private void login(final String email, final String password){
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Logging in...");
        progressDialog.show();



        StringRequest strReq = new StringRequest(Request.Method.POST,

                Utils.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (error) {
                        // Error in login. Get the error message
//                        String errorMsg = jObj.getString("error_msg");
                       Toast.makeText(Login.this,"Failed to Login",Toast.LENGTH_LONG).show();
                        progressDialog.hide();
                    } else {




                        String id = jObj.getString("id");




                        // Inserting row in users table

                        userInfo.setId(id);




                        userSession.setLoggedin(true);

                        Toast.makeText ( Login.this,"Login Successful",Toast.LENGTH_SHORT ).show ();
                        progressDialog.dismiss();


                        startActivity(new Intent(Login.this, Home.class));
                        finish();

                    }
                } catch (JSONException e) {
                    // JSON error

                    e.printStackTrace();
                    progressDialog.hide();
                   Toast.makeText(Login.this,"Unknow Error",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Login.this,"Unknown Error",Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        Singleton.getInstance (Login.this).addToRequestQueue ( strReq );
    }



}
