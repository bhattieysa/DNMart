package com.example.dn_mart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dn_mart.R;
import com.example.dn_mart.prefs.UserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ShopFragment extends Fragment {







    private RecyclerView rv;
    private List<ShopModel> list_datas;
    private ShopAdapter adapter;
    FragmentManager fragmentManager;
    JSONArray jsonArray;
    JSONObject server_responce;
    ImageView cart;
    TextView cartno;
    String Search,URLL,IDD;
    EditText searchbar;

    ImageView searchicon;
    UserInfo userInfo;


    private RequestQueue requestQueue;
    RequestQueue mQueue;

    ImageView scan;
    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v1 = inflater.inflate(R.layout.fragment_shop, container, false);
        scan= v1.findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked

                                Intent it=new Intent(getActivity(),BarcodeReader.class);
                                startActivity(it);





                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked


                                Toast.makeText(getActivity(),"Select The product From The App",Toast.LENGTH_LONG).show();


                                break;
                        }
                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are You In DN-MART Physically ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();











            }
        });




        //recycleview
        progressDialog=new ProgressDialog(getActivity());
        fragmentManager=getFragmentManager();

        rv=v1.findViewById(R.id.recylcerView);

        userInfo=new UserInfo(getActivity());
        cart = v1.findViewById(R.id.cart);
        cartno = v1.findViewById(R.id.cartNotification);
        searchicon = v1.findViewById(R.id.searchicon);
        searchbar = v1.findViewById(R.id.search);
        rv.setHasFixedSize(true);
//        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));

        list_datas=new ArrayList<>();


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                CartFragment cartFragment=new CartFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment,cartFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });




        progressDialog.setMessage("Loading...");
        progressDialog.show();


        IDD=userInfo.getKeyId();
        URLL=Utils.SHOPCATEGORY_URL+"?idd="+IDD;

        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URLL , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {




                        try {



                            jsonArray = response.getJSONArray("category");

                            //if(jsonArray==null){

                            //Toast.makeText ( getActivity (),"Donners Is Not Available",Toast.LENGTH_SHORT ).show ();

                            //}


                            for (int i=0;i<jsonArray.length();i++){
                                server_responce=jsonArray.getJSONObject(i);



                                String error= server_responce.getString ("error");

                                if (error.equals("True")) {

                                    progressDialog.hide();


                                } else {
                                    progressDialog.dismiss();
                                    String name = server_responce.getString("name");

                                    String id = server_responce.getString("id");
                                    String image = server_responce.getString("image");
                                    String count = server_responce.getString("count");
                                    Log.d("Eysa1",image);


                                    cartno.setText(count);
                                    if(count.equals("0")){
                                        cartno.setVisibility(View.INVISIBLE);
                                    }


                                    image=Utils.HOMEIMAGE_URL+image;

                                    ShopModel listData = new ShopModel(id, name, image);
                                    list_datas.add(listData);

                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hide();
                            Toast.makeText(getActivity(),e.getMessage()+"aasa",Toast.LENGTH_LONG).show();
                        }
                        setupData(list_datas);

                    }




                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(getActivity()).addToRequestQueue(request);





        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search=searchbar.getText().toString().trim();


                if(Search.isEmpty()){

                    searchbar.setError("Search Bar Can't Be Blank");
                    searchbar.requestFocus();
                    return;


                }

                fragmentManager=getFragmentManager();
                SearchBar(Search);



            }
        });

        return v1;
    }








    private void setupData(List<ShopModel> list_datas) {


        adapter=new ShopAdapter(list_datas,getActivity(),fragmentManager);
        rv.setAdapter(adapter);

    }
    public void SearchBar(String Search){

//        progressDialog.setTitle("Please wait...");
//        progressDialog.show();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();



        Bundle bundle=new Bundle (  );
        bundle.putString ( "search", Search);


        ViewSearchProducts viewSearchProducts= new ViewSearchProducts();
        viewSearchProducts.setArguments ( bundle );
        fragmentTransaction.replace(R.id.nav_host_fragment,viewSearchProducts);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }




}




