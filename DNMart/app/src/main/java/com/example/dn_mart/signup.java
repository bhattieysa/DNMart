package com.example.dn_mart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {


    EditText Name,Email,Password,Number;
    ImageView image;
    CircleImageView circleImageView;
    String name,email,password,number;
    ProgressDialog progressDialog;
    final int IMG_REQUEST = 1;
    Bitmap bitmap;


    Button Singnup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        Number=findViewById(R.id.number);
        image=findViewById(R.id.imageIcon);
        Singnup=findViewById(R.id.signup);
        circleImageView=findViewById(R.id.imageUser);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //imageHeadings=false;
                SelectImage();



            }
        });



        progressDialog=new ProgressDialog(this);


        Singnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setTitle("Please Wait...!");
                progressDialog.show();



                name=Name.getText().toString().trim();
                email=Email.getText().toString().trim();
                password=Password.getText().toString().trim();
                number=Number.getText().toString().trim();

                if(name.isEmpty()){

                    Name.setError("Name Can't Be Blank");
                    Name.requestFocus();
                    return;


                }




                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Email.setError("Enter Valid Email");
                    Email.requestFocus();
                    return;

                }

                if(email.isEmpty()){

                    Name.setError("Email Can't Be Blank");
                    Name.requestFocus();
                    return;


                }
                if(password.isEmpty()){

                    Name.setError("Password Can't Be Blank");
                    Name.requestFocus();
                    return;


                }
                if(number.isEmpty()){

                    Name.setError("Number Can't Be Blank");
                    Name.requestFocus();
                    return;


                }


                Signup(name,email,password,number);











            }
        });
    }

    public void Signup(final String Name, final String Email, final String Passwod, final String Number){






        StringRequest stringRequest=new StringRequest ( Request.Method.POST ,
                Utils.SIGNUP_URL , new Response.Listener <String> () {


            @Override
            public void onResponse(String response) {
                Log.i("tagconvertstr", "["+response+"]");

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");


                    if (!error) {
                        progressDialog.dismiss();



                        image.setImageResource(0);

                        Intent it=new Intent(signup.this,Login.class);
                        startActivity(it);
                        finish();
                        Toast.makeText ( signup.this,"Signup Successfull",Toast.LENGTH_SHORT ).show ();




                    } else {
                        // Error in login. Get the error message
                        progressDialog.hide();

                        Toast.makeText ( signup.this,"Faild to Signup",Toast.LENGTH_SHORT ).show ();

                    }
                } catch (JSONException e) {
                    progressDialog.hide();
                    // JSON error
                    e.printStackTrace();


                    Toast.makeText (signup.this,"Json error: " + e.getMessage(),Toast.LENGTH_SHORT ).show ();



                }



            }
        } , new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                //Log.e(TAG, "Login Error: " + error.getMessage());

                Toast.makeText (signup.this,error.getMessage(),Toast.LENGTH_SHORT ).show ();


            }
        }) {

            @Override
            protected Map<String, String> getParams() {


                // Posting parameters to login url


                Map<String, String> params = new HashMap<>();

                params.put("name", Name);
                params.put("email", Email);
                params.put("password", Passwod);
                params.put("phonenumber", Number);
                params.put("image", imageToString(bitmap));



                return params;


            }

        };

        // Adding request to request queue
        Singleton.getInstance( getApplicationContext() ).addToRequestQueue(stringRequest);




    }

    private void SelectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);


    }

    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);





        byte[] imageBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri path=data.getData();
        try {
            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            circleImageView.setImageBitmap(bitmap);
            circleImageView.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
