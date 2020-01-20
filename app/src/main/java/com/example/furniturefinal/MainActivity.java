package com.example.furniturefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button signin;
    ProgressBar progressBar;
    FirebaseAuth auth;
    TextView new_user_tv;
    private SignInButton signInButton;
    GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN=1;
    private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        signin = findViewById(R.id.button_signIn);
        new_user_tv = findViewById(R.id.new_user_tv);
        auth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.google);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });


        //header
       /* auth.getCurrentUser().getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
            @Override
            public void onSuccess(GetTokenResult getTokenResult) {
                getTokenResult.getToken();
            }
        });*/

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail =email.getText().toString().trim();
                String myPassword=password.getText().toString().trim();

                if(TextUtils.isEmpty(myEmail)){
                    email.setError("Email is Required");
                    return;
                }
               /* if(TextUtils.isEmpty(myPassword)){
                    password.setError("Password is Required");
                    return;
                }*/

                if(myPassword.length()<6){
                    password.setError("Password Must be Greater than Six Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user

                auth.signInWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error!" +task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        new_user_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
    }
    private void signin(){
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }
   @Override
    protected void onActivityResult(int requestCode,int resultCode,@NonNull Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

   }

   private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount googleSignInAccount=completedTask.getResult(ApiException.class);
            Toast.makeText(MainActivity.this,"Signed in Successfully",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(googleSignInAccount);
        }
        catch (ApiException e) {
            Toast.makeText(MainActivity.this,"Sign in Failed",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);
        }

   }
   private void FirebaseGoogleAuth(GoogleSignInAccount account){
       AuthCredential authCredential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
       auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                   FirebaseUser user=auth.getCurrentUser();
                   updateUI(user);
               }
               else{
                   Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                   updateUI(null);
               }
           }
       });
   }
   private void updateUI(FirebaseUser firebaseUser){
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null){
            String PersonName=account.getDisplayName();
            String personEmail=account.getEmail();
        }
   }


}
