package com.mehedi.koronaviruscausesTusu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mehedi.koronaviruscausesTusu.Adapter.DayByDayAdapter;
import com.mehedi.koronaviruscausesTusu.Responce.CoronaResponse;
import com.mehedi.koronaviruscausesTusu.Services.DayByDayService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mehedi.koronaviruscausesTusu.BangladeshDayByDayActivity.BASEURL_CORONA;
import static com.mehedi.koronaviruscausesTusu.FirstActivity.countryListNameforSearch;

public class SearchGlobalDayByDayActivity extends AppCompatActivity {

    private CoronaResponse coronaResponse;
    private List<DayByDayService> dayByDayServices=new ArrayList<>();
    private RecyclerView recyclerView;
    private DayByDayAdapter adapter1;
    private Toolbar toolbar;
    private LinearLayout golobalLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_global_day_by_day);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        golobalLL=findViewById(R.id.golobalLL);

        golobalLL.setVisibility(View.GONE);

        recyclerView=findViewById(R.id.globalDataRV);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL_CORONA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coronaResponse=retrofit.create(CoronaResponse.class);


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

                AlertDialog.Builder builder=new AlertDialog.Builder(SearchGlobalDayByDayActivity.this);
                builder.setTitle("Warning!");
                builder.setMessage("Wait a minute because it has a long data \nThanks");
                builder.show();
                golobalLL.setVisibility(View.VISIBLE);

                String url=String.format("dayone/country/%s",userTypeCountry);
                Call<List<DayByDayService>> call=coronaResponse.getDaybyDayServices(url);

                call.enqueue(new Callback<List<DayByDayService>>() {
                    @Override
                    public void onResponse(Call<List<DayByDayService>> call, Response<List<DayByDayService>> response) {
                        if (response.code()==200){
                            dayByDayServices=response.body();

                            adapter1=new DayByDayAdapter(SearchGlobalDayByDayActivity.this,dayByDayServices);

                            RecyclerView.LayoutManager manager=new LinearLayoutManager(SearchGlobalDayByDayActivity.this,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter1);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<DayByDayService>> call, Throwable t) {

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

        final Dialog dialog=new Dialog(SearchGlobalDayByDayActivity.this);
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
