package com.example.dn_mart;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dn_mart.prefs.UserInfo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.android.volley.VolleyLog.TAG;

public class HomeFragment extends Fragment {
    //home fragment slideradapter..........
    ViewPager viewPager;
    private Timer mtime;


    ImageView cart,scan;
    TextView cartno;
    EditText searchbar;
    ImageView searchicon;
    String Search,URL,IDD;
    FragmentManager fragmentManager;
    AlertDialog.Builder builder;

    //home fragment recycleview.....................................
    private RecyclerView rv;
    private List<HomeModel>list_datas;
    private HomeAdapter adapter;
    JSONArray jsonArray;
    JSONObject server_responce;
    String URLL;
    UserInfo userInfo;
    Typeface tf;


    private RequestQueue requestQueue;
    RequestQueue mQueue;


    private ProgressDialog progressDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v1 = inflater.inflate(R.layout.fragment_home, container, false);

        //recycleview
        progressDialog=new ProgressDialog(getActivity());
        fragmentManager=getFragmentManager();
        rv=v1.findViewById(R.id.recylcerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        list_datas=new ArrayList<>();

        cartno=v1.findViewById(R.id.cartNotification);
        cart = v1.findViewById(R.id.cart);
        scan = v1.findViewById(R.id.scan);
        cartno = v1.findViewById(R.id.cartNotification);
        searchicon = v1.findViewById(R.id.searchicon);
        searchbar = v1.findViewById(R.id.search);
        mQueue = Volley.newRequestQueue(getActivity());


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





        // for home slider adapter

        viewPager = v1.findViewById(R.id.viewpager);
        homefSliderAdapter viewPagerAdapter=new homefSliderAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        userInfo=new UserInfo(getActivity());
        timetask timetask = new timetask();

        mtime = new Timer();
        mtime.schedule(timetask,2000,4000);


// recyclerview volley code
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        IDD=userInfo.getKeyId();


        URLL=Utils.HOMEPRODUCTS_URL+"?idd="+IDD;




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


                                } else {
                                    progressDialog.dismiss();
                                    String name = server_responce.getString("name");
                                    String price = server_responce.getString("price");
                                    String description = server_responce.getString("des");
                                    String quantity = server_responce.getString("quantity");
                                    String discount = server_responce.getString("discount");
                                    String image = server_responce.getString("image");
                                    String id = server_responce.getString("id");
                                    String count = server_responce.getString("count");
                                    String flag ="HOME";

                                        cartno.setText(count);
                                       if(count.equals("0")){
                                            cartno.setVisibility(View.INVISIBLE);
                                       }





                                    image=Utils.HOMEIMAGE_URL+image;

                                    HomeModel listData = new HomeModel(image, name, discount, quantity, description, price,id,flag);
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

    //home slider
    public class timetask extends TimerTask {

        @Override
        public void run() {

            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

//                        if (viewPager.getCurrentItem() == 0) {
//                            viewPager.setCurrentItem(1);
//
//                        } else if (viewPager.getCurrentItem() == 1) {
//                            viewPager.setCurrentItem(2);
//                        } else if (viewPager.getCurrentItem() == 2) {
//                            viewPager.setCurrentItem(0);
//                        }
//                        int a = viewPager.getAdapter().getCount();
                        int a= new homefSliderAdapter(getContext()).getCount();
                        if(viewPager.getCurrentItem()+1==a){
                            viewPager.setCurrentItem(0);
                        }else {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    }
                });
            }

        }



    }


    private void setupData(List<HomeModel> list_datas) {


        adapter=new HomeAdapter(list_datas,getActivity());
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
    public void myToast() {
        Toast.makeText(getActivity(), "This is called from MainActivity", Toast.LENGTH_SHORT).show();
    }

    private void strikeThroughText(TextView price){
        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);

            IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if (result.getContents()==null){
                Toast.makeText(getActivity(),"Reasult Not Found",Toast.LENGTH_LONG).show();

            }else {
                androidx.appcompat.app.AlertDialog.Builder alertdialougebuilder=new androidx.appcompat.app.AlertDialog.Builder(getActivity());
                alertdialougebuilder.setMessage(result.getContents()+"\n\nScan Again ?");
                alertdialougebuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Scannow();
                    }
                });


                alertdialougebuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                    }
                });
                androidx.appcompat.app.AlertDialog alertDialog=alertdialougebuilder.create();
                alertDialog.show();

            }

        }


        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if(result!=null){
//            if (result.getContents()==null){
//                Toast.makeText(getActivity(),"Reasult Not Found",Toast.LENGTH_LONG).show();
//
//            }else {
//                androidx.appcompat.app.AlertDialog.Builder alertdialougebuilder=new androidx.appcompat.app.AlertDialog.Builder(getActivity());
//                alertdialougebuilder.setMessage(result.getContents()+"\n\nScan Again ?");
//                alertdialougebuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Scannow();
//                    }
//                });
//
//
//                alertdialougebuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //finish();
//                    }
//                });
//                androidx.appcompat.app.AlertDialog alertDialog=alertdialougebuilder.create();
//                alertDialog.show();
//
//            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    public void Scannow(){


        IntentIntegrator integrator=new IntentIntegrator(getActivity());
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






