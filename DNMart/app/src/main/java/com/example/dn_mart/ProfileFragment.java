package com.example.dn_mart;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.dn_mart.prefs.UserInfo;
import com.example.dn_mart.prefs.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

CircleImageView image;
TextView name,phone,email,history,wishlist;

    JSONArray jsonArray;

    RequestQueue mQueue;
    Button logout;
    FragmentManager fragManager;
ProgressDialog progressDialog;
UserInfo userInfo;
UserSession userSession;

String Userid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v1= inflater.inflate(R.layout.fragment_profile, container, false);
         image=v1.findViewById(R.id.image);
        name=v1.findViewById(R.id.name);
        phone=v1.findViewById(R.id.phonenumber);
        history=v1.findViewById(R.id.orderlist);
        email=v1.findViewById(R.id.email);
        logout=v1.findViewById(R.id.logout);
        wishlist=v1.findViewById(R.id.wishlist);
        progressDialog=new ProgressDialog(getActivity());

        userInfo=new UserInfo(getActivity());
        userSession=new UserSession(getActivity());

         Userid=userInfo.getKeyId();
         wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i=new Intent(getActivity(),WishlistHistory.class);
                startActivity(i);



            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


Intent i=new Intent(getActivity(),OrderHistory.class);
startActivity(i);




            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userSession.setLoggedin(false);
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();


            }
        });



        loadProfile();










       return v1;
    }



    private  void loadProfile(){

        progressDialog.setMessage("Loading...");
        progressDialog.show();


            StringRequest strReq = new StringRequest(Request.Method.POST,
                    Utils.PROFILE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject server_responce = new JSONObject(response);
                    boolean error = server_responce.getBoolean("error");

                    // Check for error node in json
                        if(error){

                        progressDialog.hide();
                        Toast.makeText ( getActivity(),"Internet Error",Toast.LENGTH_SHORT ).show ();
                        HomeFragment homeFragment=new HomeFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                        .replace(R.id.container,homeFragment )
                        .commit();




                        }else {
                        progressDialog.dismiss();
                        String Name=server_responce.getString ( "name" );
                        String phonenumber= server_responce.getString ("number");
                        String Email=server_responce.getString ("email");
                        String Image=server_responce.getString ("image");

                        String  imagee=Utils.IMAGE_URL+Image;

                        name.setText(Name);
                        phone.setText(phonenumber);
                        email.setText(Email);

                        Glide.with(getActivity())
                        .load(imagee)

                        .into(image);





                        }
                } catch (JSONException e) {
                    // JSON error

                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(getActivity(),"Unknow Error",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),"Unknown Error",Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("id", Userid);


                return params;
            }

        };

        // Adding request to request queue
        Singleton.getInstance (getActivity()).addToRequestQueue ( strReq );


    }


    }



