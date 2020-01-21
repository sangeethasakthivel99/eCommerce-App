package com.example.furniturefinal.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.furniturefinal.R;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
 TextView name,email,password,address,contactNumber;
 Button save;
 String server_url="http://ip address/file name";
AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name=findViewById(R.id.editName);
        email=findViewById(R.id.editEmail);
        password=findViewById(R.id.editPassword);
        address=findViewById(R.id.editAddress);
        contactNumber=findViewById(R.id.editPhoneNumber);
        save=findViewById(R.id.save);
        builder=new AlertDialog.Builder(UserActivity.this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mname,memail,mpassword,maddress,mcontactNumber;
                mname=name.getText().toString();
                maddress=address.getText().toString();
                mpassword=password.getText().toString();
                memail=email.getText().toString();
                mcontactNumber=contactNumber.getText().toString();
                StringRequest stringRequest =new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response: "+ response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText("");
                                email.setText("");
                                address.setText("");
                                contactNumber.setText("");
                                password.setText("");
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("name",mname);
                        params.put("email",memail);
                        params.put("address",maddress);
                        params.put("contactNumber",mcontactNumber);
                        params.put("password",mpassword);
                        return params;
                    }
                };

            }
        });

    }
}
