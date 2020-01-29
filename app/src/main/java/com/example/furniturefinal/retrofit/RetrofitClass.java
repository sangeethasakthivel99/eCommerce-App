package com.example.furniturefinal.retrofit;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClass {
    private static OkHttpClient client = null;
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new FirebaseUserIdTokenInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.16.29.144:8111/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    static class FirebaseUserIdTokenInterceptor implements Interceptor {
        // Custom header for passing ID token in request.
        private static final String X_FIREBASE_ID_TOKEN = "token";
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            try {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Task<GetTokenResult> task = user.getIdToken(true);
                    GetTokenResult tokenResult = Tasks.await(task);
                    String idToken = tokenResult.getToken();
                    if (idToken == null) {
//                        throw new Exception("idToken is null");
                    } else {
                        Request modifiedRequest = request.newBuilder()
                                .addHeader(X_FIREBASE_ID_TOKEN, idToken)
                                .build();
                        return chain.proceed(modifiedRequest);
                    }
                }
            } catch (Exception e) {
//                throw new IOException(e.getMessage());
            }
            return chain.proceed(request);
        }
    }
}