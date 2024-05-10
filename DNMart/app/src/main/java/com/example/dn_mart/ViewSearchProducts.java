package com.example.dn_mart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewSearchProducts extends Fragment {
    String id,name,price,description,quantity,discount,image,search;
    ProgressDialog progressDialog;
    JSONArray jsonArray;
    JSONObject server_responce;
    RequestQueue mQueue;
    String URL;


    private RecyclerView rv;
    private List<SearchModel> list_datas;
    private SearchAdapter adapter;
    FragmentManager fragmentManager;
    UserInfo userInfo;


    ImageView scan,searchicon;
    EditText searchbar;
    TextView cartno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v1= inflater.inflate(R.layout.fragment_view_search_products, container, false);
//        name=getArguments ().getString ( "name" );
//        price=getArguments ().getString ( "Price" );
//        description=getArguments ().getString ( "description" );
//        quantity=getArguments ().getString ( "quantity" );
//        discount=getArguments ().getString ( "discount" );
//        id=getArguments ().getString ( "id" );
//        image=getArguments ().getString ( "image" );
//
//        Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
        searchicon = v1.findViewById(R.id.searchicon);
        searchbar = v1.findViewById(R.id.search);
        scan = v1.findViewById(R.id.scan);


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



        search=getArguments ().getString ( "search" );


        Toast.makeText(getActivity(),search,Toast.LENGTH_SHORT).show();


        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search=searchbar.getText().toString().trim();


                if(search.isEmpty()){

                    searchbar.setError("Search Bar Can't Be Blank");
                    searchbar.requestFocus();
                    return;


                }

                fragmentManager=getFragmentManager();
                SearchBar(search);



            }
        });






        progressDialog=new ProgressDialog(getActivity());



        fragmentManager=getFragmentManager();
        rv=v1.findViewById(R.id.recylcerView);
        rv.setHasFixedSize(true);
        userInfo=new UserInfo(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        list_datas=new ArrayList<>();



        mQueue = Volley.newRequestQueue(getActivity());

        progressDialog.setTitle("Please wait...");
        progressDialog.show();


        URL=Utils.SEARCH_BAR_URL+"?product="+search;



        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URL, null,
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


                                    Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_LONG).show();
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


                                  SearchModel listData = new SearchModel(id, name, image,price,discount,quantity,description);
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
    private void setupData(List<SearchModel> list_datas) {


        adapter=new SearchAdapter(list_datas,getActivity(),fragmentManager,userInfo);
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
