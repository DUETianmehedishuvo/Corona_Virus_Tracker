package com.mehedi.koronaviruscausesTusu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import static com.mehedi.koronaviruscausesTusu.FirstActivity.countryListNameforSearch;

public class Search_Spicify_Country_Activity extends AppCompatActivity {


    private CoronaResponse coronaResponse;
    private java.text.DecimalFormat format;
    private LinearLayout searchLL;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__spicify__country_);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        format=new DecimalFormat("#,##,###");
        searchLL=findViewById(R.id.searchLL);
        searchLL.setVisibility(View.GONE);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,countryListNameforSearch);

        AutoCompleteTextView actv =  (AutoCompleteTextView)findViewById(R.id.searchSpecefyCountryACTV);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);



        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userTypeCountry=adapter.getItem(position).toString();
                searchLL.setVisibility(View.VISIBLE);
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(FirstActivity.BASE_URL_ALL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                coronaResponse=retrofit.create(CoronaResponse.class);

                String urlString=String.format("countries/%s",userTypeCountry);
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
                            Picasso.with(Search_Spicify_Country_Activity.this).load(Uri.parse(bangladeshDataService.getCountryInfo().getFlag()))
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
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_doya:
                openDoya();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDoya() {

        final Dialog dialog=new Dialog(Search_Spicify_Country_Activity.this);
        dialog.setContentView(R.layout.info_row);
        TextView title=dialog.findViewById(R.id.infoTitleTV);
        ImageButton closeBtn=dialog.findViewById(R.id.infoCloseBTN);
        TextView contentTV=dialog.findViewById(R.id.infoContentTV);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        title.setText("সরাসরি কোরআন হাদিসের আলাকে চিকিৎসা Ayat E Shifa Dua প্লেগ মহামারি করোনা ভাইরাস ইত্যাদির দোয়া");
        contentTV.setText("Hadith Moton -1\nHadith: Sahih Abu Daud- 1554 (Tahqiq Albani)\n" +
                "\n" +
                "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْبَرَصِ وَالْجُنُونِ وَالْجُذَامِ وَمِنْ سَيِّئِ الأَسْقَامِ\n" +
                "English: Narrated Anas ibn Malik: The Prophet (S) used to say: “O Allah I seek refuge in Thee from leprosy, madness, elephantiasis, and evil diseases.\n" +
                "\n" +
                "Bangla Pronunciation: আল্লাহুম্মা ইন্নি আউযুবিকা মিনাল বারসি ওয়াল জুনুনি ওয়াল জুজামি ওয়া মিন ছাইয়্যি ইল আসকম\n" +
                "\n" +
                "Bangla: আনাস (রাঃ) সূত্রে বর্ণিত। নাবী সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম বলতেনঃ হে আল্লাহ, আমি আপনার কাছে আশ্রয় চাই শ্বেত, উন্মাদনা, কুষ্ঠ এবং সমস্ত দুরারোগ্য ব্যাধি হতে।\n" +
                "\n" +
                "Tahqiq: Nasir Udding Albani Collectied this Hadith in Sahih Abu Daud, আবূ ঈসা বলেন, এ হাদীসটি হাসান গারীব। আমরা এ হাদীসটি শুধুমাত্র আবূল মিনহালের সূত্রে জেনেছি।\n\n\n" +
                "Hadith: Nasai- Islamic Foundattion-5494, নাসায়ী (অধ্যায় : আশ্রয় প্রার্থনা, হাঃ ৫৪৯৪), Hadithbd -5492\n" +
                "\n" +
                "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ الْجُنُونِ وَالْجُذَامِ وَالْبَرَصِ وَسَيِّئِ الْأَسْقَامِ\n" +
                "English: It was narrated from Anas that: The Prophet [SAW] used to say: “Allahumma inni a’udhu bika minal-jununi wal-jadhami, wal-barasi wa sayy’il-asqam (O Allah, I seek refuge in You from possession, leprosy, leukederma and bad sickness (that may lead to visible deformity).”\n" +
                "\n" +
                "Bangla Pronunciation: আল্লাহুম্মা ইন্নি আউযুবিকা মিনাল জুনুনি ওয়াল জুজামি ওয়াল বারসি ওয়া ছাইয়্যি ইল আসকম\n" +
                "\n" +
                "Bangla: মুহাম্মাদ ইবন মুসান্না (রহঃ) … আনাস (রাঃ) থেকে বর্ণিত যে, নবী সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম বলতেনঃ হে আল্লাহ্\u200C! আমি আপনার নিকট আশ্রয় প্রার্থনা করছি পাগলামী, কুষ্ঠ রোগ এবং শ্বেতরোগ এবং অতি মন্দ রোগ ব্যাধি হতে।\n\n\n" +
                "Hadith: Sahih Miskat -2470(Tahqiq Albani)\n" +
                "\n" +
                "اَللّٰهُمَّ إِنِّىْ أَعُوذُ بِكَ مِنَ الْبَرَصِ وَالْجُذَامِ وَالْجُنُونِ وَمِنْ سَيِّئِ الْأَسْقَامِ.\n" +
                "Bangla : আনাস (রাঃ) হতে বর্ণিত। তিনি বলেন, রসূলুল্লাহ সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম (দু‘আয়) বলতেনঃ ‘‘আল্ল-হুম্মা ইন্নী আ‘ঊযুবিকা মিনাল বারাসি, ওয়াল জুযা-মি, ওয়াল জুনূনি, ওয়ামিন্ সাইয়্যিয়িল আসক্বা-ম’’ (অর্থাৎ- হে আল্লাহ! আমি শ্বেতরোগ, কুষ্ঠরোগ, উম্মাদনা ও কঠিন রোগসমূহ হতে তোমার কাছে আশ্রয় প্রার্থনা করি)\n" +
                "\n" +
                "Hadith More: (ইবনু আবী শায়বাহ্ ২৯১২৯, ইরওয়া ৩/৩৫৭–৩৫৮, আহমাদ ১৩০০৪, সহীহ ইবনু হিব্বান ১০৯৭, সহীহ আল জামি‘ ১২৮১ ");


        dialog.show();
    }
}
