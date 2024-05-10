package com.example.dn_mart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView rv;
    private List<HistoryModel> list_datas;
    private HistoryAdapter adapter;

    JSONArray jsonArray;
    JSONObject server_responce;


    private RequestQueue requestQueue;
    RequestQueue mQueue;
    String Search,URLL,IDD;
    EditText searchbar;
    ProgressDialog progressDialog;

    ImageView searchicon;
    UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_order_history);


        progressDialog=new ProgressDialog(OrderHistory.this);

        rv=findViewById(R.id.recylcerView);

        userInfo=new UserInfo(OrderHistory.this);



        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(OrderHistory.this, LinearLayoutManager.VERTICAL,false));
        //rv.setLayoutManager(new GridLayoutManager(OrderHistory.this,2));

        list_datas=new ArrayList<>();

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        IDD=userInfo.getKeyId();
        URLL=Utils.HISTORY_URL+"?id="+IDD;


        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URLL , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {




                        try {



                            jsonArray = response.getJSONArray("products");

                            //if(jsonArray==null){

                            //Toast.makeText ( getActivity (),"Donners Is Not Available",Toast.LENGTH_SHORT ).show ();

                            //}


                            for (int i=0;i<jsonArray.length();i++){
                                server_responce=jsonArray.getJSONObject(i);



                                String error= server_responce.getString ("error");

                                if (error.equals("True")) {

                                    progressDialog.hide();
                                    Toast.makeText(OrderHistory.this,"NO order in history",Toast.LENGTH_SHORT).show();

                                } else {
                                    progressDialog.dismiss();
                                    String name = server_responce.getString("name");

                                    String o_id = server_responce.getString("o_id");
                                    String price = server_responce.getString("price");
                                    String date = server_responce.getString("date");
                                    String status = server_responce.getString("status");








                                    HistoryModel listData = new HistoryModel(o_id, name, price,status,date);
                                    list_datas.add(listData);

                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hide();
                            Toast.makeText(OrderHistory.this,e.getMessage()+"aasa",Toast.LENGTH_LONG).show();
                        }
                        setupData(list_datas);

                    }




                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderHistory.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(OrderHistory.this).addToRequestQueue(request);







    }

    private void setupData(List<HistoryModel> list_datas) {



        adapter=new HistoryAdapter(list_datas,getApplicationContext());
        rv.setAdapter(adapter);

    }





}
