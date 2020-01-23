package com.example.furniturefinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.furniturefinal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
 TextView mname,memail,maddress,mcontactNumber;
 Button save;
 Button image_Button;
 ImageView imageUrl;
 String server_url="http://ip address/file name";
AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mname=findViewById(R.id.editName);
        memail=findViewById(R.id.editEmail);
       // password=findViewById(R.id.editPassword);
        image_Button=findViewById(R.id.imageButton);
        maddress=findViewById(R.id.editAddress);
        mcontactNumber=findViewById(R.id.editPhoneNumber);
        imageUrl=findViewById(R.id.image);
        save=findViewById(R.id.save);
        builder=new AlertDialog.Builder(UserActivity.this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,email,password,address,contactNo;
                name=mname.getText().toString();
                address=maddress.getText().toString();
               // mpassword=password.getText().toString();
                email=memail.getText().toString();
                contactNo=mcontactNumber.getText().toString();
                StringRequest stringRequest =new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response: "+ response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mname.setText("");
                                memail.setText("");
                                maddress.setText("");
                                mcontactNumber.setText("");
                                //password.setText("");
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
                        params.put("name",name);
                        params.put("email",email);
                        params.put("address",address);
                        params.put("contactNumber",contactNo);
                        params.put("imageUrl",getImageUrl());
                       // params.put("password",password);
                        return params;
                    }
                };

            }
        });
        image_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,CaptureImage.class);
                startActivity(intent);
            }
        });

    }

    private String getImageUrl(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainsRef = storageRef.child("icon.jpg");
        final String[] generatedImageUrl = {""};
//        Bitmap bitmap = BitmapFactory.decodeResource(UserActivity.this.getResources(),R.drawable.icon);
        Bitmap bitmap = BitmapFactory.decodeResource(UserActivity.this.getResources(), R.drawable.icon);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] data = byteArrayOutputStream.toByteArray();
        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                if(downloadUri.isSuccessful() && downloadUri.getResult()!=null){
                    Toast.makeText(UserActivity.this, ""+downloadUri.getResult().toString(), Toast.LENGTH_SHORT).show();
                    generatedImageUrl[0] = downloadUri.getResult().toString();
                }

            }
        });
        return generatedImageUrl[0];
    }
}
