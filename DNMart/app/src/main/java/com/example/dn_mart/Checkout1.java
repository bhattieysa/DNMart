package com.example.dn_mart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.android.volley.toolbox.Volley;
import com.example.dn_mart.prefs.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Checkout1 extends AppCompatActivity {

    ImageView back,pay;




    //home fragment recycleview.....................................
    private RecyclerView rv;
    private List<InvoiceModel> list_datas;
    private InvoiceAdapter adapter;
    JSONArray jsonArray;
    JSONObject server_responce;

    UserInfo userInfo;
    Typeface tf;



    String ID,URLL,URLL1;
    TextView customername,date,shipping,total,subtotal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_checkout1);

        final String o_id = getIntent().getStringExtra("o_id");

        final ProgressDialog progressDialog;

        back=findViewById(R.id.back);
        pay=findViewById(R.id.pay);
        customername=findViewById(R.id.customername);
        date=findViewById(R.id.date);
        shipping=findViewById(R.id.shipping);
        total=findViewById(R.id.total);
        subtotal=findViewById(R.id.subtotal);
        userInfo=new UserInfo(this);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                if(total.getText()!="40000") {
                                    String name = getIntent().getStringExtra("name");
                                    String address = getIntent().getStringExtra("address");
                                    String number = getIntent().getStringExtra("number");

                                    Intent it = new Intent(Checkout1.this, Checkout2.class);

                                    it.putExtra("name", name);
                                    it.putExtra("address", address);
                                    it.putExtra("number", number);
                                    it.putExtra("total", total.getText());
                                    it.putExtra("o_id", o_id);

                                    startActivity(it);
                                }else {
                                    Toast.makeText(Checkout1.this,"Internet Error",Toast.LENGTH_SHORT).show();
                                }




                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked


                               // Toast.makeText(Checkout1.this,"Select The product From The App",Toast.LENGTH_LONG).show();


                                break;
                        }
                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(Checkout1.this);
                builder.setMessage("Are You Sure To Place Order ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();




            }
        });



        rv=findViewById(R.id.recylcerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(Checkout1.this, LinearLayoutManager.VERTICAL,false));
        list_datas=new ArrayList<>();


        ID=userInfo.getKeyId();
        URLL1=Utils.INVOICEback+"?id="+ID+"&o_id="+o_id;

        progressDialog=new ProgressDialog(Checkout1.this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setTitle("Please wait...");
                progressDialog.show();



                final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URLL1 , null,
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

                                            Toast.makeText(Checkout1.this,"Internet Error",Toast.LENGTH_LONG).show();
                                            progressDialog.hide();

                                        } else {


                                            onBackPressed();

                                        }

                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    Toast.makeText(Checkout1.this,e.getMessage()+"aasa",Toast.LENGTH_LONG).show();
                                }
                                setupData(list_datas);

                            }




                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Checkout1.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

                Singleton.getInstance(Checkout1.this).addToRequestQueue(request);






            }
        });




        URLL=Utils.INVOICE+"?id="+ID+"&o_id="+o_id;




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

                                   Toast.makeText(Checkout1.this,"Internet Error",Toast.LENGTH_LONG).show();


                                } else {


                                    String product_name = server_responce.getString("product_name");
                                    String quantity = server_responce.getString("quantity");
                                    String price = server_responce.getString("price");
                                    String product_id = server_responce.getString("product_id");
                                    String amount = server_responce.getString("amount");
                                    String Shipping = server_responce.getString("shipping");
                                    String Date= server_responce.getString("date");
                                    String Customer_name = server_responce.getString("customer_name");
                                    String Subtotal = server_responce.getString("subtotal");



                                    customername.setText(Customer_name);
                                    date.setText(Date);
                                    shipping.setText(Shipping);
                                    subtotal.setText(Subtotal);

                                    int a=Integer.valueOf(Shipping);
                                    int b=Integer.valueOf(Subtotal);

                                  int  Total=a+b;

                                  String Total1=String.valueOf(Total);

                                  total.setText(Total1);




                                    InvoiceModel listData = new InvoiceModel (product_name, quantity,price,product_id,amount);
                                    list_datas.add(listData);






                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(Checkout1.this,e.getMessage()+"aasa",Toast.LENGTH_LONG).show();
                        }
                        setupData(list_datas);

                    }




                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Checkout1.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Singleton.getInstance(Checkout1.this).addToRequestQueue(request);








    }
    private void setupData(List<InvoiceModel> list_datas) {


        adapter=new InvoiceAdapter(list_datas,Checkout1.this);
        rv.setAdapter(adapter);

    }
}
