package com.example.dn_mart;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;


public class AfterShopList extends Fragment {

    String id,name;
    ProgressDialog progressDialog;
    JSONArray jsonArray;
    JSONObject server_responce;
    RequestQueue mQueue;
    String URL;

    private RecyclerView rv;
    private List<AfterShopListModel> list_datas;
    private AfterShopListAdapter adapter;
    FragmentManager fragmentManager;
    UserInfo userInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1= inflater.inflate(R.layout.fragment_after_shop_list, container, false);

              progressDialog=new ProgressDialog(getActivity());

        name=getArguments ().getString ( "name" );
        id=getArguments ().getString ( "id" );




        fragmentManager=getFragmentManager();
        rv=v1.findViewById(R.id.recylcerView);
        rv.setHasFixedSize(true);
        userInfo=new UserInfo(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        list_datas=new ArrayList<>();



        mQueue = Volley.newRequestQueue(getActivity());

        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        URL=Utils.AFTERSHOP_URL+"?id="+id;




        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URL , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Login Response: " + response.toString());


                        try {



                            jsonArray = response.getJSONArray("products");

                            //if(jsonArray==null){

                            //Toast.makeText ( getActivity (),"Donners Is Not Available",Toast.LENGTH_SHORT ).show ();

                            //}


                            for (int i=0;i<jsonArray.length();i++){
                                server_responce=jsonArray.getJSONObject(i);



                                String error= server_responce.getString ("error");

                                if (error.equals("true")) {

                                    Toast.makeText(getActivity(),"Products not found",Toast.LENGTH_LONG).show();
                                    progressDialog.hide();


                                } else {
                                    progressDialog.dismiss();
                                    String name = server_responce.getString("name");
                                    String price = server_responce.getString("price");
                                    String description = server_responce.getString("des");
                                    String quantity = server_responce.getString("quantity");
                                    String discount = server_responce.getString("discount");
                                    String image = server_responce.getString("image");
                                    String id = server_responce.getString("id");


                                    image=Utils.HOMEIMAGE_URL+image;

                                    AfterShopListModel listData = new AfterShopListModel(id, name, image,price,discount,quantity,description);
                                    list_datas.add(listData);


//


                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hide();
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        setupData(list_datas);
                    }




                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        mQueue.add(request);









        return v1;
    }
    private void setupData(List<AfterShopListModel> list_datas) {


        adapter=new AfterShopListAdapter(list_datas,getActivity(),fragmentManager,userInfo);
        rv.setAdapter(adapter);

    }
}
