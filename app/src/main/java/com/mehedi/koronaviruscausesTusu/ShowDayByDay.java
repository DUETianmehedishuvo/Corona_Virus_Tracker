package com.mehedi.koronaviruscausesTusu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehedi.koronaviruscausesTusu.Responce.CoronaResponse;
import com.mehedi.koronaviruscausesTusu.Services.BangladeshDataService;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mehedi.koronaviruscausesTusu.FirstActivity.conveterEngToBan;

public class ShowDayByDay extends AppCompatActivity {

    private CoronaResponse coronaResponse;
    private java.text.DecimalFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day_by_day);


        format=new DecimalFormat("#,##,###");


        String country=getIntent().getStringExtra("country");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(FirstActivity.BASE_URL_ALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coronaResponse=retrofit.create(CoronaResponse.class);

        String urlString=String.format("countries/%s",country);
        Call<BangladeshDataService> call=coronaResponse.getCountryAdapter(urlString);
        call.enqueue(new Callback<BangladeshDataService>() {
            @Override
            public void onResponse(Call<BangladeshDataService> call, Response<BangladeshDataService> response) {
                if (response.code()==200){
                    Log.e("Show Day By Day", "onResponse: " );

                    BangladeshDataService bangladeshDataService=response.body();

                    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                    cal.setTimeInMillis(bangladeshDataService.getUpdated());
                    String date = DateFormat.format("d MMM yyyy HH:mm:ss aaa", cal).toString();
                    ((TextView)findViewById(R.id.showDateTV)).setText("Last Updated: "+date);
                    Picasso.with(ShowDayByDay.this).load(Uri.parse(bangladeshDataService.getCountryInfo().getFlag()))
                            .into(((ImageView)findViewById(R.id.showCountryIV)));
                    ((TextView)findViewById(R.id.showCountryTitle)).setText(bangladeshDataService.getCountry());
                    ((TextView)findViewById(R.id.showCasesTV)).setText(conveterEngToBan(bangladeshDataService.getCases()));
                    ((TextView)findViewById(R.id.showDeathsTV)).setText(conveterEngToBan(bangladeshDataService.getDeaths()));
                    ((TextView)findViewById(R.id.showRecorveredTV)).setText(conveterEngToBan(bangladeshDataService.getRecovered()));
                    ((TextView)findViewById(R.id.showActiveTV)).setText(conveterEngToBan(bangladeshDataService.getActive()));
                    ((TextView)findViewById(R.id.showCricticalTV)).setText(conveterEngToBan(bangladeshDataService.getCritical()));
                    ((TextView)findViewById(R.id.showCasesPOMTV)).setText(format.format(bangladeshDataService.getCasesPerOneMillion()));
                    ((TextView)findViewById(R.id.showDeathsPOMTV)).setText(format.format(bangladeshDataService.getDeathsPerOneMillion()));
                    ((TextView)findViewById(R.id.showBCasesTV)).setText(conveterEngToBan(bangladeshDataService.getTodayCases()));
                    ((TextView)findViewById(R.id.showBDeathsTV)).setText(conveterEngToBan(bangladeshDataService.getTodayDeaths()));


                }
            }

            @Override
            public void onFailure(Call<BangladeshDataService> call, Throwable t) {
                Log.e("Show Day By Day", "onFailure: " );
            }
        });

    }
}
