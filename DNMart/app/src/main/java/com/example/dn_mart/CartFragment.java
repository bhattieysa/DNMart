package com.example.dn_mart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dn_mart.R;
import com.example.dn_mart.prefs.UserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static com.android.volley.VolleyLog.TAG;

public class CartFragment extends Fragment {
    String id,name;
    ProgressDialog progressDialog;
    JSONArray jsonArray;
    JSONObject server_responce;
    RequestQueue mQueue;
    String URL;
    private RecyclerView recyclerView;
    private List<CartModel> list_datas;
    private CartAdapter adapter;
    private CartAdapter iadapter;
    FragmentManager fragmentManager;
    UserInfo userInfo;
    Button checkout;
    TextView total;
    int pos=0;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);


        navView.getMenu().findItem(R.id.cart).setChecked(true);

        View v1 = inflater.inflate(R.layout.fragment_cart, container, false);



        progressDialog=new ProgressDialog(getActivity());





        fragmentManager=getFragmentManager();
        recyclerView=v1.findViewById(R.id.recylcerView);
        checkout=v1.findViewById(R.id.checkout);
        total=v1.findViewById(R.id.total);
        userInfo=new UserInfo(getActivity());
        mQueue = Volley.newRequestQueue(getActivity());
        id=userInfo.getKeyId();



        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(),"as",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getActivity(),Checkout.class);
                startActivity(intent);

            }
        });


final String TOTAL=Utils.TOTAL+"?id="+id;
        final JsonObjectRequest request1=new JsonObjectRequest(Request.Method.GET,TOTAL , null,
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


                                    Toast.makeText(getActivity(),"Cart is empty",Toast.LENGTH_LONG).show();
                                    progressDialog.hide();


                                } else {
                                    progressDialog.dismiss();
                                    String Total = server_responce.getString("total");
                                    total.setText(Total);


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

        mQueue.add(request1);



















        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        list_datas=new ArrayList<>();





        progressDialog.setTitle("Please wait...");
        progressDialog.show();


        URL=Utils.CART_URL+"?id="+id;




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

                                if (error.equals("TRUE")) {


                                    Toast.makeText(getActivity(),"Cart Is Empty",Toast.LENGTH_LONG).show();
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
                                    String cartquantity = server_responce.getString("cartquantity");


                                    image=Utils.HOMEIMAGE_URL+image;

                                    CartModel listData = new CartModel(id, name, image,price,discount,quantity,description,cartquantity);
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


        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                   int  position = viewHolder.getAdapterPosition();

                list_datas.remove(position);
                adapter.notifyItemRemoved(position);
                String a = (String) viewHolder.itemView.getTag();
                String user = userInfo.getKeyId();

                String uURL=Utils.DELETECART_URL+"?id="+a+"&user_id="+user;


                final JsonObjectRequest request2=new JsonObjectRequest(Request.Method.GET,uURL , null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "Login Response: " + response.toString());


                                try {



                                    jsonArray = response.getJSONArray("products");



                                    for (int i=0;i<jsonArray.length();i++){
                                        server_responce=jsonArray.getJSONObject(i);



                                        String error= server_responce.getString ("error");

                                        if (error.equals("true")) {


                                            Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_LONG).show();



                                        }else {

                                            Toast.makeText(getActivity(),"deleted successfully",Toast.LENGTH_LONG).show();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                            CartFragment cartFragment = new CartFragment();

                                            fragmentTransaction.replace(R.id.nav_host_fragment, cartFragment);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();


                                        }

                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                                }


                            }




                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

                mQueue.add(request2);






            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                       .addBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorRed))
                        .addActionIcon(R.drawable.delete_sweep)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);






        return v1;
    }
    private void setupData(List<CartModel> list_datas) {


        adapter=new CartAdapter(list_datas,getActivity(),fragmentManager,userInfo,0);

        recyclerView.setAdapter(adapter);






    }

}
