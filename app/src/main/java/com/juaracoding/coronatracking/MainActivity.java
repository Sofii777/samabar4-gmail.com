package com.juaracoding.coronatracking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juaracoding.coronatracking.model.covid19app.TrackingCorona;
import com.juaracoding.coronatracking.service.APIClient;
import com.juaracoding.coronatracking.service.APIInterfacesRest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText txtNama, txtUmur, txtDomisili, txtTelepon;
    Button btnRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = findViewById(R.id.txtNama);
        txtUmur = findViewById(R.id.txtUmur);
        txtDomisili = findViewById(R.id.txtDomisili);
        txtTelepon = findViewById(R.id.txtTelpon);
        btnRegistrasi = findViewById(R.id.btnRegistrasi);

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //postDataPengguna(txtNama.getText().toString(),txtUmur.getText().toString(),txtDomisili.toString(),txtTelepon.toString());

            }
        });

    }
    public RequestBody toRequestBody(String value){
        if(value == null){
            value="";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),value);
        return body;
    }
    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;



    public void postDataPengguna(String username,String latitude,String longitude,String timestamp, String status,String nama, String umur,String gender,String domisili,String telepon ,String kondisi){


        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<TrackingCorona> call3 = apiInterface.postPengguna(toRequestBody(username),toRequestBody(latitude),toRequestBody(longitude),toRequestBody(timestamp),toRequestBody(status),toRequestBody(nama),toRequestBody(umur),toRequestBody(gender), toRequestBody(domisili), toRequestBody(telepon), toRequestBody(kondisi));
        call3.enqueue(new Callback<TrackingCorona>() {
            @Override
            public void onResponse(Call<TrackingCorona> call, Response<TrackingCorona> response) {
                progressDialog.dismiss();
                TrackingCorona data = response.body();

                if (data !=null) {


                    Toast.makeText(MainActivity.this,data.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Kondisi.class);
                    startActivity(intent);




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<TrackingCorona> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }
}
