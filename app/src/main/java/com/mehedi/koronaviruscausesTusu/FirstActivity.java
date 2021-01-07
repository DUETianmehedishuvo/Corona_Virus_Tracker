package com.mehedi.koronaviruscausesTusu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mehedi.koronaviruscausesTusu.Adapter.CountryAdapter;
import com.mehedi.koronaviruscausesTusu.Responce.CoronaResponse;
import com.mehedi.koronaviruscausesTusu.Services.BangladeshDataService;
import com.mehedi.koronaviruscausesTusu.Services.TotalDataServices;
import com.mehedi.koronaviruscausesTusu.Services.WorldAllDataServices;
import com.mehedi.koronaviruscausesTusu.SharePreferenche.UserPreferenche;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout line;
    private Toolbar toolbar;


    private TextView mTcasesTV,mTDeadsTV,mTRecorvedTV,mlUpdateTV,aCasesTV,aDeadsTV,bCasesTV,bDeadsTV,bRecorvedTV,baCasesTV;
    public static final String BASE_URL_ALL="https://corona.lmao.ninja/v2/";
    private CoronaResponse coronaResponse;
    public static List<WorldAllDataServices> worldAllDataServices;
    private String tCases,tDeads,tRecorvered;

    private UserPreferenche userPreferenche;

    private static java.text.DecimalFormat format;
    private LinearLayout bdLinearLayout;
    public static List<String> countryListNameforSearch =new ArrayList<>();



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        line=findViewById(R.id.line);
        NavigationView navigationView=findViewById(R.id.navigation_menu);



        userPreferenche=new UserPreferenche(this);
        bdLinearLayout=findViewById(R.id.bdLinearLayout);

        format=new DecimalFormat("#,##,###");

        mTcasesTV=findViewById(R.id.mTcasesTV);
        mTDeadsTV=findViewById(R.id.mTDeadsTV);
        mTRecorvedTV=findViewById(R.id.mTrecorvedTV);
        mlUpdateTV=findViewById(R.id.mLastUpdateTV);
        aCasesTV=findViewById(R.id.aCasesTV);
        aDeadsTV=findViewById(R.id.aDeadsTV);
        bCasesTV=findViewById(R.id.bCasesTV);
        bDeadsTV=findViewById(R.id.bDeatsTV);
        bRecorvedTV=findViewById(R.id.bRecorveredTV);
        baCasesTV=findViewById(R.id.bacasesTV);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL_ALL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        coronaResponse=retrofit.create(CoronaResponse.class);
        Call<TotalDataServices> call=coronaResponse.getTotalData();
        call.enqueue(new Callback<TotalDataServices>() {
            @Override
            public void onResponse(Call<TotalDataServices> call, Response<TotalDataServices> response) {
                if (response.code()==200){
                    TotalDataServices totalDataServices=response.body();


                    mTcasesTV.setText(conveterEngToBan(totalDataServices.getCases()));
                    mTDeadsTV.setText(conveterEngToBan(totalDataServices.getDeaths()));
                    mTRecorvedTV.setText(conveterEngToBan(totalDataServices.getRecovered()));


                    tCases=mTcasesTV.getText().toString();
                    tDeads=mTDeadsTV.getText().toString();
                    tRecorvered=mTRecorvedTV.getText().toString();


                    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                    cal.setTimeInMillis(totalDataServices.getUpdated());
                    String date = "Last Updated: "+ DateFormat.format("d MMM yyyy hh:mm:ss aaa", cal).toString();
                    mlUpdateTV.setText(date);


                    userPreferenche.saveTotalData(tCases,tDeads,tRecorvered,date);
                }
            }

            @Override
            public void onFailure(Call<TotalDataServices> call, Throwable t) {
                Log.e("Failur", "onFailure: "+t.getMessage() );


                mTcasesTV.setText(userPreferenche.getTMCases());
                mTDeadsTV.setText(userPreferenche.getTMDeads());
                mTRecorvedTV.setText(userPreferenche.getTMRecorvered());
                mlUpdateTV.setText(userPreferenche.getTTime());

            }
        });

        Call<List<WorldAllDataServices>> call1=coronaResponse.getWorldAllDataServices();
        call1.enqueue(new Callback<List<WorldAllDataServices>>() {
            @Override
            public void onResponse(Call<List<WorldAllDataServices>> call, Response<List<WorldAllDataServices>> response) {
                if (response.code()==200){

                    worldAllDataServices=response.body();


                    int todayCases=0;
                    int todayDeads=0;
                    for (int i=0;i<worldAllDataServices.size()-1;i++){
                        Log.e("World All Data", "Country: "+worldAllDataServices.get(i).getCountry() );
                        todayCases=todayCases+worldAllDataServices.get(i).getTodayCases();
                        todayDeads=todayDeads+worldAllDataServices.get(i).getTodayDeaths();
                        countryListNameforSearch.add(worldAllDataServices.get(i).getCountry());

                    }

                    aCasesTV.setText(conveterEngToBan(todayCases));
                    aDeadsTV.setText(conveterEngToBan(todayDeads));

                    userPreferenche.saveTodayData(format.format(todayCases),format.format(todayDeads));
                }
            }

            @Override
            public void onFailure(Call<List<WorldAllDataServices>> call, Throwable t) {
                Log.e("World All Services", "onFailure: "+t.getMessage() );


                aCasesTV.setText(userPreferenche.getTACases());
                aDeadsTV.setText(userPreferenche.getTADeads());

            }
        });


        Call<BangladeshDataService> call2=coronaResponse.getBangladeshService();
        call2.enqueue(new Callback<BangladeshDataService>() {
            @Override
            public void onResponse(Call<BangladeshDataService> call, Response<BangladeshDataService> response) {
                if (response.code()==200){
                    final BangladeshDataService bangladeshDataService=response.body();
                    Log.e("Bangladesh Data", "onResponse: " );

                    bCasesTV.setText(conveterEngToBan(bangladeshDataService.getCases()));
                    bDeadsTV.setText(conveterEngToBan(bangladeshDataService.getDeaths()));
                    bRecorvedTV.setText(conveterEngToBan(bangladeshDataService.getRecovered()));
                    baCasesTV.setText(conveterEngToBan(bangladeshDataService.getTodayCases()));


                    bdLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bangladeshDataService==null){
                                Toast.makeText(FirstActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {
                                Dialog dialog=new Dialog(FirstActivity.this);
                                dialog.setContentView(R.layout.list_row);

                                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                                cal.setTimeInMillis(bangladeshDataService.getUpdated());
                                String date = DateFormat.format("d MMM yyyy HH:mm:ss aaa", cal).toString();

                                TextView showDateTV=dialog.findViewById(R.id.showDateTV);
                                TextView showCountryTitle=dialog.findViewById(R.id.showCountryTitle);
                                TextView showCasesTV=dialog.findViewById(R.id.showCasesTV);
                                TextView showDeathsTV=dialog.findViewById(R.id.showDeathsTV);
                                TextView showRecorveredTV=dialog.findViewById(R.id.showRecorveredTV);
                                TextView showActiveTV=dialog.findViewById(R.id.showActiveTV);
                                TextView showCricticalTV=dialog.findViewById(R.id.showCricticalTV);
                                TextView showCasesPOMTV=dialog.findViewById(R.id.showCasesPOMTV);
                                TextView showDeathsPOMTV=dialog.findViewById(R.id.showDeathsPOMTV);
                                TextView showBCasesTV=dialog.findViewById(R.id.showBCasesTV);
                                TextView showBDeathsTV=dialog.findViewById(R.id.showBDeathsTV);
                                ImageView showImageV=dialog.findViewById(R.id.showCountryIV);

                                showDateTV.setText("Last Updated: "+date);
                                Picasso.with(FirstActivity.this).load(Uri.parse(bangladeshDataService.getCountryInfo().getFlag()))
                                        .into(showImageV);

                                showCountryTitle.setText(bangladeshDataService.getCountry());
                                showCasesTV.setText(format.format(bangladeshDataService.getCases()));
                                showDeathsTV.setText(format.format(bangladeshDataService.getDeaths()));
                                showRecorveredTV.setText(format.format(bangladeshDataService.getRecovered()));
                                showActiveTV.setText(format.format(bangladeshDataService.getActive()));
                                showCricticalTV.setText(format.format(bangladeshDataService.getCritical()));
                                showCasesPOMTV.setText(format.format(bangladeshDataService.getCasesPerOneMillion()));
                                showDeathsPOMTV.setText(format.format(bangladeshDataService.getDeathsPerOneMillion()));
                                showBCasesTV.setText(format.format(bangladeshDataService.getTodayCases()));
                                showBDeathsTV.setText(format.format(bangladeshDataService.getTodayDeaths()));

                                Log.e("Item Click", "Bangladeshi Item Cicked: "+format.format(bangladeshDataService.getCases()) );
                                dialog.show();
                            }
                        }
                    });

                    userPreferenche.saveBangladeshiData(
                            format.format(bangladeshDataService.getCases()),
                            format.format(bangladeshDataService.getDeaths()),
                            format.format(bangladeshDataService.getRecovered()),
                            format.format(bangladeshDataService.getTodayCases()));

                }
            }

            @Override
            public void onFailure(Call<BangladeshDataService> call, Throwable t) {
                Log.e("Bangladesh Data", "onFailure: " );


                bCasesTV.setText(userPreferenche.getTBCases());
                bDeadsTV.setText(userPreferenche.getTBDeads());
                bRecorvedTV.setText(userPreferenche.getTBRecorvered());
                baCasesTV.setText(userPreferenche.getTBTCases());
            }
        });


        Window window=getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float sidebar=drawerLayout.getWidth()*slideOffset;
                line.setTranslationX(sidebar);
            }
        };

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(FirstActivity.this,FirstActivity.class));
                        break;
                    case R.id.action_lastCondition:
                        openShowAllData();
                        break;
                    case R.id.action_flag:
                        openFlagSearch();
                        break;
                    case R.id.action_search_specify_country:
                        startActivity(new Intent(FirstActivity.this,Search_Spicify_Country_Activity.class));
                        break;
                    case R.id.action_bangladesh_dayByDayondition:
                        startActivity(new Intent(FirstActivity.this,BangladeshDayByDayActivity.class));
                        break;
                    case R.id.action_dayByDayConditionWorls:
                        startActivity(new Intent(FirstActivity.this,SearchGlobalDayByDayActivity.class));
                        break;
                    case R.id.action_donate:
                        startActivity(new Intent(FirstActivity.this,DonateActivity.class));
                        break;
                    case R.id.action_aboutDeveloper:
                        startActivity(new Intent(FirstActivity.this,AboutUs.class));
                        break;

                }

                return false;

            }
        });

    }

    private void openShowAllData() {

        startActivity(new Intent(FirstActivity.this,ShowAllCountry.class)
                .putExtra("causes",tCases).putExtra("deads",tDeads).putExtra("recorvered",tRecorvered));

    }

    private void openFlagSearch() {

        if (worldAllDataServices==null){
            Toast.makeText(this, "Check Internet Connection.", Toast.LENGTH_SHORT).show();

        }else {

            final Dialog dialog=new Dialog(this);
            dialog.setContentView(R.layout.select_country_row);
            final ListView listView=dialog.findViewById(R.id.selectCountryLV);
            CountryAdapter adapter=new CountryAdapter(this,worldAllDataServices);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(FirstActivity.this,ShowDayByDay.class).putExtra("country",worldAllDataServices.get(position).getCountry()));
                    Log.e("selected Country", "onItemClick: "+worldAllDataServices.get(position).getCountry() );
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }

    public static String conveterEngToBan(int getNumber){

        String userNumber=format.format(getNumber)
                .replaceAll("0","০")
                .replaceAll("1","১")
                .replaceAll("2","২")
                .replaceAll("3","৩")
                .replaceAll("4","৪")
                .replaceAll("5","৫")
                .replaceAll("6","৬")
                .replaceAll("7","৭")
                .replaceAll("8","৮")
                .replaceAll("9","৯");

        return userNumber;
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

        final Dialog dialog=new Dialog(FirstActivity.this);
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

    public void infoQuestion(View view) {

        final Dialog dialog=new Dialog(FirstActivity.this);
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

        switch (view.getId()){
            case R.id.question1:
                title.setText("করোনা ভাইরাস (কোভিড-১৯) কি?");
                contentTV.setText("নভেল করোনাভাইরাস (সিওভি) হলো করোনাভাইরাসের এক নতুন প্রজাতি।\n" +
                        "\n" +
                        "নভেল করোনাভাইরাসের মাধ্যমে সৃষ্ট এই রোগটি প্রথম চীনের উহানে চিহ্নিত হয়েছিল। তখন থেকেই রোগটির নাম করা হয়েছিল করোনাভাইরাস রোগ ২০১৯ (কোভিড-১৯)। করোনা থেকে ‘কো’ , ভাইরাস থেকে ‘ভি’, এবং ‘ডিজিজ’ বা ‘রোগ’ থেকে ‘ডি’ নিয়ে এর সংক্ষিপ্ত নামকরণ করা হয়। আগে, এই রোগকে ‘২০১৯ নভেল করোনাভাইরাস’ বা ‘২০১৯-এনসিওভি’ বলা হতো।\n" +
                        "\n" +
                        "কোভিড-১৯ হলো একটি নতুন ভাইরাস যা অতীতের সার্স ভাইরাস এবং কয়েক ধরনের সাধারণ সর্দি-জ্বর জাতীয় ভাইরাসের পরিবারভুক্ত।");
                break;
            case R.id.question9:
                title.setText("করোনাভাইরাসের লক্ষণগুলো কী?");
                contentTV.setText("রেসপিরেটরি লক্ষণ ছাড়াও জ্বর, কাশি, শ্বাস প্রশ্বাসের সমস্যাই মূলত প্রধান লক্ষণ।\n" +
                        "\n" +
                        "এটি ফুসফুসে আক্রমণ করে।\n" +
                        "\n" +
                        "সাধারণত শুষ্ক কাশি ও জ্বরের মাধ্যমেই শুরু হয় উপসর্গ দেখা দেয়, পরে শ্বাস প্রশ্বাসে সমস্যা দেখা দেয়।\n" +
                        "\n" +
                        "সাধারণত রোগের উপসর্গগুলো প্রকাশ পেতে গড়ে পাঁচদিন সময় নেয়।\n" +
                        "বিশ্ব স্বাস্থ্য সংস্থা বলছে, ভাইরাসটির ইনকিউবেশন পিরিয়ড ১৪দিন পর্যন্ত স্থায়ী থাকে। তবে কিছু কিছু গবেষকের মতে এর স্থায়িত্ব ২৪দিন পর্যন্ত থাকতে পারে।\n" +
                        "\n" +
                        "মানুষের মধ্যে যখন ভাইরাসের উপসর্গ দেখা দেবে তখন বেশি মানুষকে সংক্রমণের সম্ভাবনা থাকবে তাদের। তবে এমন ধারণাও করা হচ্ছে যে নিজেরা অসুস্থ না থাকার সময়ও সুস্থ মানুষের দেহে ভাইরাস সংক্রমিত করতে পারে মানুষ।\n" +
                        "\n" +
                        "শুরুর দিকের উপসর্গ সাধারণ সর্দিজ্বর এবং ফ্লু'য়ের সাথে সাদৃশ্যপূর্ণ হওয়ায় রোগ নির্ণয়ের ক্ষেত্রে দ্বিধাগ্রস্থ হওয়া স্বাভাবিক।\n" +
                        "\n" +
                        "করোনাভাইরাসের প্রাদুর্ভাব অনেককে সার্স ভাইরাসের কথা মনে করিয়ে দিয়েছে যা ২০০০ সালের শুরুতে প্রধানত এশিয়ার অনেক দেশে ৭৭৪ জনের মৃত্যুর কারণ হয়েছিলো ।\n" +
                        "\n" +
                        "নতুন ভাইরাসটির জেনেটিক কোড বিশ্লেষণ করে দেখা গেছে এটি অনেকটাই সার্স ভাইরাসের মতো।\n" +
                        "\n" +
                        "\"আমরা যখন নতুন কোনো করোনাভাইরাস দেখি, তখন আমরা জানতে চাই এর লক্ষ্মণগুলো কতটা মারাত্মক। এ ভাইরাসটি অনেকটা ফ্লুর মতো কিন্তু সার্স ভাইরাসের চেয়ে মারাত্মক নয়,\" বলছিলেন এডিনবার্গ বিশ্ববিদ্যালয়ের প্রফেসর মার্ক উলহাউস।\n" +
                        "\n");
                break;
            case R.id.question2:
                title.setText("করোনা ভাইরাস প্রতিরোধে করণীয়");
                contentTV.setText("আমাদের করণীয়-\n" +
                        "১.ঘরে থাকি।\n" +
                        "২. বাইরে বের হলে নিয়মগুলো মানি। মাস্ক ব্যবহার করি।\n" +
                        "৩. তিন লেয়ারের সার্জিক্যাল মাস্ক উপরের নিয়মে ধুয়ে ব্যবহার করতে পারি (যদি বাধ্য হই)।\n" +
                        "৪. বাইরে থেকে ঘরে ফেরার পর পোশাক ধুয়ে ফেলি। বা না ঝেড়ে ঝুলিয়ে রাখি অন্তত চার ঘণ্টা।\n" +
                        "৫. প্লাস্টিকের তৈরি পিপিই বা চোখ মুখ, মাথা একবার ব্যবহারের পর অবশ্যই ডিটারজেন্ট দিয়ে ভালো করে ধুয়ে শুকিয়ে ব্যবহার করা যেতে পারে (যদি বাধ্য হই)।\n" +
                        "৬. কাপড়ের তৈরি পিপিই বা বর্ণিত নিয়মে পরিষ্কার করে পড়ি (যদি বাধ্য হই (কেননা এগুলো একবার ব্যবহারের জন্য তৈরি)।\n" +
                        "৭. চুল সম্পূর্ণ ঢাকে এমন মাথার ক্যাপ ব্যবহার করি।\n" +
                        "৮. হাঁচি কাশি যাদের আছে সরকার হতে প্রচারিত সব নিয়ম মেনে চলি। এছাড়াও খাওয়ার জিনিস, তালা চাবি, সুইচ ধরা, মাউস, রিমোট কন্ট্রোল, মোবাইল ফোন, ঘড়ি, কম্পিউটার ডেক্স, টিভি ইত্যাদি ধরা এবং বাথরুম ব্যবহারের আগে ও পরে নির্দেশিত মতে হাত ধুয়ে নিন। যাদের হাত শুকনো থাকে তারা হাত ধোয়ার পর Moisture ব্যবহার করি।\n" +
                        "\n" +
                        "কেননা শুকনো হাতের Crackle (ফাটা অংশ) এর ফাঁকে এই ভাইরাস থেকে যায়। অতি ক্ষারযুক্ত সাবান বা ডিটারজেন্ট ব্যবহার থেকে বিরত থাকি। ");
                break;
            case R.id.question3:
                title.setText("করোনা ভাইরাস প্রতিরোধে হাত ধোয়ার নিয়ম");
                contentTV.setText("ধাপ ১: প্রবাহমান পানিতে হাত ভেজানো;\n" +
                        "\n" +
                        "ধাপ ২: ভেজা হাতে পর্যাপ্ত পরিমান সাবান ব্যবহার করা;\n" +
                        "\n" +
                        "ধাপ ৩: হাতের পেছনের অংশ, আঙ্গুলের মধ্যের অংশ এবং নখের নিচের অংশসহ হাতের সব অংশই অন্ততপক্ষে ২০ সেকেন্ড ভালোভাবে ধুয়ে ফেলা;\n" +
                        "\n" +
                        "ধাপ ৪: প্রবাহমান পানিতে ভালভাবে কচলে হাত ধোয়া;\n" +
                        "\n" +
                        "ধাপ ৫: একটি পরিষ্কার কাপড় বা এককভাবে ব্যবহার করেন এমন তোয়ালে দিয়ে হাত ভালোভাবে মুছে ফেলা।\n" +
                        "\n" +
                        "আপনার হাত ঘন ঘন ধুবেন। বিশেষ করে, খাবার আগে, নাক পরিস্কার করার পর, কাশি বা হাঁচি দেওয়ার পর এবং বাথরুমে যাওয়ার পরেও।\n" +
                        "\n" +
                        "সাবান ও পানি যদি সহজে পাওয়া না যায়, সেক্ষেত্রে কমপক্ষে ৬০ শতাংশ অ্যালকোহল রয়েছে এমন অ্যালকোহলভিত্তিক হ্যান্ড স্যানিটাইজার ব্যবহার করুন। যদি হাতে ময়লা থাকে, তবে সব সময় সাবান ও পানি দিয়ে হাত ধুয়ে ফেলুন।");
                break;
            case R.id.question4:
                title.setText("কোভিড-১৯ আক্রান্ত সন্দেহভাজন কারা?");
                contentTV.setText("এই ভাইরাসের সংক্রমণে জ্বর (৮৩%-৯৩% রোগীর ক্ষেত্রে), শুকনো কাশি (৭৬%-৮২% রোগীর ক্ষেত্রে), অবসাদ বা পেশীতে ব্যথা (১১%-৪৪% রোগীর ক্ষেত্রে), এবং পরবর্তীতে শ্বাসকষ্ট ও শ্বাসনালীর রোগ (যেমন- ক্লোমনালীর প্রদাহ তথা ব্রঙ্কাইটিস এবং নিউমোনিয়া) হয়। কদাচিৎ মাথাব্যথা, তলপেটে ব্যথা, উদরাময় (ডায়রিয়া) বা কফসহ কাশি হতে পারে। রোগীদের রক্ত পরীক্ষা করে দেখা গেছে এই ভাইরাসের কারণে তাদের শ্বেত রক্তকণিকার সংখ্যা হ্রাস পায়। এছাড়া যকৃৎ ও বৃক্কের (কিডনি) ক্ষতি হয়। সাধারণত এক সপ্তাহের আগ পর্যন্ত উপসর্গগুলি ডাক্তার দেখানোর মত জটিল রূপ ধারণ করে না। কিন্তু ২য় সপ্তাহে এসে ব্যক্তিভেদে অবস্থার দ্রুত ও গুরুতর অবনতি ঘটতে পারে। যেমন ফুসফুসের ক্ষতিবৃদ্ধির সাথে সাথে ধমনীর রক্তে অক্সিজেনের স্বল্পতা (হাইপক্সেমিয়া) দেখা দেয় এবং রোগীকে অক্সিজেন চিকিৎসা দিতে হয়। এছাড়া তীব্র শ্বাসকষ্টমূলক রোগলক্ষণসমষ্টি (অ্যাকিউট রেসপিরেটরি ডিসট্রেস সিনড্রোম) পরিলক্ষিত হয়। ২৫ থেকে ৩০ শতাংশ রোগীকে নিবিড় পরিচর্যা কেন্দ্রে (intensive care unit বা ICU) রেখে যান্ত্রিকভাবে শ্বাসগ্রহণ করাতে হয় এবং কখনও কখনও কৃত্রিম ফুসফুসের ভেতরে রক্ত পরিচালনার মাধ্যমে রক্তে অক্সিজেন যোগ করতে হয়। এছাড়া ফুসফুসের ব্যাপক ক্ষতি হবার কারণে ব্যাকটেরিয়াঘটিত ২য় একটি নিউমোনিয়া হবার বড় সম্ভাবনা থাকে এবং নিবিড় পরিচর্যাধীন রোগীদের ১০% ক্ষেত্রে এটি হয়।");
                break;
            case R.id.question5:
                title.setText("কোয়ারেন্টাইন ও আইসোলেসন কি?");
                contentTV.setText("আইসোলেশন: কারও শরীরে করোনার লক্ষণ প্রকাশ পেলে এবং টেস্টের রিপোর্ট পজিটিভ হলে, মোট কথা করোনা হয়েছে ধরা পড়লে তাকে আইসোলেশনে পাঠানোর ব্যবস্থা করা হয়। আইসোলেশনের সময় চিকিৎসক ও নার্সদের তত্ত্বাবধানে হাসপাতালে থাকতে হবে রোগীকে। অন্য রোগীদের কথা ভেবে হাসপাতালে আলাদা জায়গা তৈরি করা হয় তাদের জন্য। অন্তত ১৪ দিনের মেয়াদে আইসোলেশন চলে। অসুখের গতিপ্রকৃতি দেখে তা বাড়ানোও হয়।\n" +
                        "\n" +
                        "আইসোলেশনে থাকা রোগীর সঙ্গে বাইরের কারও যোগাযোগ করতে দেয়া হয় না। তাদের পরিবার-পরিজনের সাথেও এই সময় দেখা করতে দেয়া হয় না। একান্ত দেখা করতে দেওয়া হলেও অনেক বিধিনিষেধ মেনে।\n" +
                        "\n" +
                        "এই অসুখের কোনও প্রতিষেধক এখনও সন্ধানে নেই। তবে আক্রান্ত ব্যক্তিকে এই সময় কিছু অ্যান্টি-ভাইরাল ওষুধ দিয়ে, শরীরের রোগ প্রতিরোধ ক্ষমতা বাড়ানো যায় এমন কিছু ওষুধ ও পথ্য দিয়ে সুস্থ করে তোলার চেষ্টা করা হয়। যাদের শরীরে এই রোগ প্রতিরোধ ক্ষমতা বেশি ও করোনার প্রকোপ অল্প, তারা এই পদ্ধতিতে সুস্থও হন। তবে যাদের রোগে প্রতিরোধ ক্ষমতা কম তাদের পক্ষে সেরে ওঠাটা কঠিন হয়ে দাঁড়ায়।\n" +
                        "\n" +
                        "কোয়ারেন্টাইন: করোনার জীবাণু শরীরে প্রবেশ করার পরেই তার উপসর্গ দেখা দেয় না। অন্তত সপ্তাহখানেক সে ঘাপটি মেরে বসে থাকতে জানে। তাই কোনও ব্যক্তি করোনা আক্রান্ত দেশ থেকে ঘুরে এলে বা রোগীর সংস্পর্শে এলে তার শরীরেও করোনাভাইরাস বাসা বাঁধতে পারে। বাসা আদৌ বেঁধেছে কি না বা সে আক্রান্ত কি না এটা বুঝে নিতেই কোয়ারেন্টাইনে পাঠানো হয় রোগীকে। অন্য রোগীদের কথা ভেবেই কোয়ারেন্টাইন কখনও হাসপাতালে আয়োজন করা হয় না। করোনা হতে পারে এমন ব্যক্তিকে সরকারি কোয়ারেন্টাইন পয়েন্টে রাখা হয়।\n" +
                        "\n" +
                        "কমপক্ষে ১৪ দিনের সময়সীমা এখানেও। এই সময় রোগের আশঙ্কা থাকে শুধু, তাই কোনও রকম ওষুধপত্র দেয়া হয় না। শুধু স্বাস্থ্যবিধি মেনে চলতে বলা হয়। বাইরে বের হওয়া বন্ধ করতে পরামর্শ দেওয়া হয়। যেহেতু রোগের জীবাণু ভেতরে থাকতেও পারে, তাই মাস্ক ব্যবহার করতেও বলা হয়। বাড়ির লোকেদেরও এই সময় রোগীর সাথে কম যোগাযোগ রাখতে বলা হয়।");
                break;
            case R.id.question6:
                title.setText("হোম কোয়ারেন্টাইন কি?");
                contentTV.setText("হোম কোয়ারেন্টাইন: বিশেষজ্ঞরা বলছেন, ‘হোম আইসোলেশন বলে কিছু হয় না। আইসোলেশন বাড়িতে রেখে সম্ভবও নয়। বরং হোম কোয়ারেন্টাইন বলাটা অনেক যুক্তিযুক্ত। কোনও ব্যক্তি যখন নিজের বাড়িতেই কোয়রান্টাইনের সব নিয়ম মেনে, বাইরের লোকজনের সাথে ওঠাবসা বন্ধ করে আলাদা থাকেন, তখন তাকে হোম কোয়ারেন্টাইন বলে। সম্প্রতি আক্রান্ত দেশ থেকে ঘুরে না এলে রোগীকে হোম কোয়রান্টাইনে রাখা হয়।  এক্ষেত্রেও ন্যূনতম ১৪ দিন ধরে আলাদা থাকার কথা। কোনও ব্যক্তি করোনা আক্রান্ত দেশ থেকে ঘুরে এলে, বা রোগীর সংস্পর্শে এলে তার শরীরেও বাসা বাঁধতে পারে কোভিড-১৯। বাসা আদৌ বেঁধেছে কি না বা সে আক্রান্ত কি না এটা বুঝে নিতেই এই ব্যবস্থা নিতে হয়।");
                break;
            case R.id.question7:
                title.setText("কোভিড-১৯ নিয়ন্ত্রণে আইনের প্রয়োগ");
                contentTV.setText("বিদেশ থেকে আসা কিছু ব্যক্তি সরকার নির্দেশিত কোয়ারেন্টিন শর্ত সঠিকভাবে মানছেন না। অনেকেই মিথ্যা তথ্য ও গুজব রটিয়ে বিভ্রান্তি ছড়াচ্ছেন। দেশব্যাপী করোনাভাইরাসের সংক্রমণ প্রতিরোধের অংশ হিসেবে এসব ব্যক্তির বিরুদ্ধে শাস্তিমূলক ব্যবস্থা গ্রহণের কথা জানিয়েছে স্বাস্থ্য অধিদফতর।\n" +
                        "\n" +
                        "তারা বলেছে, কেউ ভাইরাসের প্রাদুর্ভাব নিয়ন্ত্রণে বাধা দিলে বা নির্দেশ পালনে অসম্মতি জানালে তাকে তিন মাস কারাদণ্ড, অনূর্ধ্ব ৫০ (পঞ্চাশ) হাজার টাকা জরিমানা বা উভয় দণ্ড দেয়া হবে।\n" +
                        "\n" +
                        "করোনাভাইরাসের মতো সংক্রমণ রোগ নিয়ন্ত্রণে হোম কোয়ারেন্টিন বা স্বেচ্ছায় গৃহবাস সবচেয়ে ভালো উপায় বলে মন্তব্য করেছেন অধিদফতরের মহাপরিচালক অধ্যাপক ডা. আবুল কালাম আজাদ।\n" +
                        "\n" +
                        "এদিকে লক্ষণ দেখা দিলে আইইডিসিআরে না এসে বাসা থেকে ফোন করার অনুরোধ জানানো হয়েছে প্রতিষ্ঠানটির পক্ষ থেকে। আক্রান্ত রোগীদের সেবাদানে প্রস্তুত ৫ বিভাগের ১৪ হাসপাতালে ১৩৫০ শয্যা। চিকিৎসাধীন করোনাভাইরাস আক্রান্ত তিনজনের একজন সম্পূর্ণ সুস্থ হয়ে বাড়ি ফিরেছেন।\n" +
                        "\n" +
                        "ভাইরাসটির সংক্রমণের তথ্য বিশ্লেষণে দেখা গেছে, ৩০ বছরের নিচের কারও এ রোগে মৃত্যু হয়নি। তাই দেশবাসীকে আতঙ্কিত না হওয়ার পরামর্শ স্বাস্থ্য বিভাগের।\n" +
                        "\n" +
                        "বিশ্ব স্বাস্থ্য সংস্থা বৃহস্পতিবার করোনাভাইরাসকে বৈশ্বিক মহামারী ঘোষণার পর স্বাস্থ্য অধিদফতরের মহাপরিচালক গণবিজ্ঞপ্তি জারি করেছেন। সেখানে বলা হয়েছে, বিদেশ থেকে আসা কিছু প্রবাসী বা তাদের সংস্পর্শে আসা ব্যক্তিবর্গ স্বাস্থ্য অধিদফতর কর্তৃক আরোপিত কোয়ারেন্টিনের শর্ত সঠিকভাবে প্রতিপালন করছেন না। অনেকেই মিথ্যা তথ্য ও গুজব রটিয়ে বিভ্রান্তি ছড়াচ্ছেন। অধিদফতর সংশ্লিষ্ট সবাইকে বর্ণিত আইন অনুযায়ী এবং নির্দেশিত পন্থায় যথাযথভাবে দায়িত্ব পালনের অনুরোধ জানাচ্ছে। ব্যত্যয়ের ক্ষেত্রে সংক্রামক রোগ (প্রতিরোধ, নিয়ন্ত্রণ ও নির্মূল) আইন-২০১৮-এর শাস্তিমূলক ধারা প্রয়োগ করা হবে।\n" +
                        "\n" +
                        "আইনের ধারা-২৫-এ বলা হয়েছে, দায়িত্ব পালনে বাধা ও নির্দেশ পালনে অসম্মতি জানালে অপরাধ হবে। এ জন্য তাকে দণ্ড দেয়া যাবে ২৫-এর (১) যদি কোনো ব্যক্তি ক. মহাপরিচালক, সিভিল সার্জন বা ক্ষমতাপ্রাপ্ত কর্মকর্তাকে তার ওপর অর্পিত দায়িত্ব পালনে বাধা দেন বা প্রতিবন্ধকতা সৃষ্টি করেন, এবং খ. সংক্রামক রোগ প্রতিরোধ, নিয়ন্ত্রণ ও নির্মূলের উদ্দেশ্যে মহাপরিচালক, সিভিল সার্জন বা ক্ষমতাপ্রাপ্ত কর্মকর্তার নির্দেশ পালনে অসম্মতি জানান, তবে ওই ব্যক্তির অনুরূপ কাজ দণ্ডনীয় অপরাধ হিসেবে বিবেচিত হবে।\n" +
                        "\n" +
                        "(২) যদি কোনো ব্যক্তি (এই ধারার) উপধারা (১)-এর অধীন অপরাধ করেন, তবে তিনি অনূর্ধ্ব ৩ (তিন) মাস কারাদণ্ডে, বা অনূর্ধ্ব ৫০ (পঞ্চাশ) হাজার টাকা অর্থদণ্ডে, বা উভয় দণ্ডে দণ্ডিত হবেন। ধারা-২৬: মিথ্যা বা ভুল তথ্য দেয়ার অপরাধ ও দণ্ড ১. যদি কোনো ব্যক্তি সংক্রামক রোগ সম্পর্কে সঠিক তথ্য জানা থাকার পরও ইচ্ছাকৃতভাবে মিথ্যা বা ভুল তথ্য দেন তাহলে ওই ব্যক্তির অনুরূপ কাজ অপরাধ হিসেবে গণ্য হবে।\n" +
                        "\n" +
                        "২. যদি কোনো ব্যক্তি (এই ধারার) উপধারা (১)-এর অধীন কোনো অপরাধ করেন, তাহলে তিনি অনূর্ধ্ব ২ (দুই) মাস কারাদণ্ডে, বা অনূর্ধ্ব ২৫ (পঁচিশ) হাজার টাকা অর্থদণ্ডে, বা উভয় দণ্ডে দণ্ডিত হবেন। ধারা-২৭: ফৌজদারি কার্যবিধির প্রয়োগ- এই আইনের অধীন সংঘটিত অপরাধের অভিযোগ দায়ের, তদন্ত, বিচার ও আপিল নিষ্পত্তির ক্ষেত্রে ফৌজদারি কার্যবিধির বিধানাবলি প্রযোজ্য হবে।\n" +
                        "\n" +
                        "গণবিজ্ঞপ্তিতে বলা হয়, বিশ্ব স্বাস্থ্য সংস্থা করোনাভাইরাসের (কোভিড-১৯) সংক্রমণকে একটি বৈশ্বিক মহামারী ঘোষণা করেছে। বাংলাদেশের চলমান প্রস্তুতি এর সঙ্গে সঙ্গতিপূর্ণ। জনসাধারণের আতঙ্কিত হওয়ার প্রয়োজন নেই। সর্বোচ্চ সতর্কতার অংশ হিসেবে স্বাস্থ্য অধিদফতর ‘সংক্রামক রোগ (প্রতিরোধ, নিয়ন্ত্রণ ও নির্মূল) আইন, ২০১৮-এর কিছু ধারা প্রয়োগের প্রয়োজন হতে পারে।\n" +
                        "\n" +
                        "আইন অনুযায়ী যা স্বাস্থ্য অধিদফতরের অধিকারের মধ্যে রয়েছে: ধারা-১(চ): বাসগৃহ, অন্যান্য গৃহ, ক্লিনিক, হাসপাতাল এবং রোগ নির্ণয় কেন্দ্র বা কোনো স্থাপনায় সংক্রামক রোগে আক্রান্ত ব্যক্তিকে সেবা প্রদান করলে বা অনুরূপ রোগে সংক্রমণের আধার হিসেবে বিবেচিত হলে উক্ত স্থান বা স্থাপনা পরিদর্শন ও তদনুযায়ী প্রয়োজনীয় ব্যবস্থা গ্রহণ।\n" +
                        "\n" +
                        "ধারা-১(জ): সংক্রামক রোগের তথ্য আছে এমন কোনো ব্যক্তিকে উক্ত রোগের বিষয়ে অধিদফতরে পাঠাতে হবে। ধারা-১(ট): সংক্রামক রোগে আক্রান্ত হয়েছেন এমন সন্দেহভাজন ব্যক্তিকে নির্দিষ্ট হাসপাতাল, অস্থায়ী হাসপাতাল, স্থাপনা বা গৃহে অন্তরীণ রাখা বা পৃথককরণ (Isolation); ধারা-১(ত): সংক্রামক রোগের বিস্তার রোধে উড়োজাহাজ, জাহাজ, জলযান, বাস, ট্রেন ও অন্যান্য যানবাহন দেশে আগমন, নির্গমন বা দেশের অভ্যন্তরে এক স্থান থেকে অন্য স্থানে চলাচল নিষিদ্ধকরণ।\n" +
                        "\n" +
                        "ধারা-১০: সংক্রামক রোগের তথ্য প্রদান: ১. যদি কোনো চিকিৎসক সংক্রামক রোগে আক্রান্ত ব্যক্তির চিকিৎসার দায়িত্বে নিয়োজিত থাকেন এবং উক্ত রোগে আক্রান্ত হয়ে মৃত্যুবরণকারী ব্যক্তি, কোনো বাসগৃহ, প্রাঙ্গণ বা এলাকায় সংক্রামক রোগের অস্তিত্ব সম্পর্কে অবহিত হন, তাহলে তিনি বিষয়টি সংশ্লিষ্ট সিভিল সার্জনকে অবহিত করবেন ২. যদি কোনো বোর্ডিং, আবাসিক হোটেল বা অস্থায়ী বাসস্থানের মালিক বা দায়িত্বপ্রাপ্ত ব্যক্তির যুক্তিসঙ্গত কারণে ধারণা হয় যে, উক্ত স্থানে বসবাসকারী কোনো ব্যক্তি সংক্রামক রোগে আক্রান্ত হয়েছেন, তাহলে তিনি অনতিবিলম্বে বিষয়টি সংশ্লিষ্ট সিভিল সার্জন এবং জেলা প্রশাসককে অবহিত করবেন।\n" +
                        "\n" +
                        "ধারা-১১: সংক্রমিত এলাকা ঘোষণা প্রবেশ নিয়ন্ত্রণ, ইত্যাদি ২. মহাপরিচালক বা ক্ষমতাপ্রাপ্ত কর্মচারীর যদি প্রতীয়মান হয়, যথাযথভাবে স্বাস্থ্য সুরক্ষা ব্যবস্থা গ্রহণ করে তাৎক্ষণিক কোনো সংক্রামক রোগ সীমিত বা নির্মূল করা সম্ভব নয়, তাহলে তিনি সংক্রমিত ব্যক্তির সংস্পর্শে বা সংক্রমিত স্থানে অন্য কোনো ব্যক্তির প্রবেশ নিষিদ্ধ, সীমিত বা নিয়ন্ত্রণ করতে পারবেন।\n" +
                        "\n" +
                        "ধারা-১৪: রোগাক্রান্ত ব্যক্তিকে সাময়িক বিছিন্নকরণ- ক্ষমতাপ্রাপ্ত কর্মচারীরা যদি মনে করে, কোনো সংক্রমিত ব্যক্তিকে বিচ্ছিন্ন করা না হলে তার মাধ্যমে অন্য ব্যক্তি সংক্রমিত হতে পারে, তাহলে উক্ত ব্যক্তিকে সাময়িকভাবে অন্য কোনো স্থানে স্থানান্তর বা জনবিচ্ছিন্ন করা যাবে।\n" +
                        "\n" +
                        "ধারা-১৮: যানবাহন জীবাণুমুক্ত করার আদেশ প্রদানের ক্ষমতা- ক্ষমতাপ্রাপ্ত কর্মচারী যদি বিশ্বাস করে যে, কোনো যানবাহন সংক্রামক জীবাণু দ্বারা আক্রান্ত হয়েছে বা সংক্রামক জীবাণুর উপস্থিতি রয়েছে, তাহলে তিনি উক্ত যানবাহন জীবাণুমুক্তকরণে গাড়ির মালিক বা স্বত্বাধিকারী বা তত্ত্বাবধায়ককে নির্দেশ প্রদান করতে পারবেন।\n" +
                        "\n" +
                        "ধারা-২০: মৃতদেহের সৎকার- ১. যদি কোনো ব্যক্তি সংক্রামক রোগে মৃত্যুবরণ করেন তাহলে উক্ত ব্যক্তির মৃতদেহ ক্ষমতাপ্রাপ্ত কর্মচারীর নির্দেশনা মোতাবেক দাফন বা সৎকার করতে হবে।\n" +
                        "\n" +
                        "সারা দেশে পরীক্ষাগার দরকার নেই : স্বাস্থ্য অধিদফতরের মহাপরিচালক প্রফেসর ডা. আবুল কালাম আজাদ বলেছেন সারা দেশে করোনাভাইরাস শনাক্তের পরীক্ষাগার তৈরির প্রয়োজন নেই। কারণ, ভাইরাস শনাক্তের সঙ্গে চিকিৎসার কোনো সম্পর্ক নেই। কোথাও সন্দেহজনক রোগী পাওয়া গেলে অধিদফতরের কর্মীরা নমুনা সংগ্রহ করবেন। এ ধরনের পরীক্ষা সারা দেশে সম্প্রসারিত হলে সংশ্লিষ্টদের জীবন ঝুঁকিতে পড়বে।\n" +
                        "\n" +
                        "তিনি বলেন, কেউ করোনাভাইরাস সংক্রান্ত কোনো মিথ্যা তথ্য দেবেন না। এতে সমস্যার সৃষ্টি হবে।\n" +
                        "\n" +
                        "অধ্যাপক আজাদ মাস্ক তৈরির জন্য সংশ্লিষ্ট প্রতিষ্ঠানগুলোকে আহ্বান জানিয়ে বলেছেন, দেশের বিভিন্ন ভাইরোলজি ল্যাবে কর্মরত গবেষকদের সঙ্গে পরামর্শ করা হয়েছে। পপলিনের কাপড় দিয়ে তিনটি স্তরবিশিষ্ট এ মাস্ক তৈরি করা যাবে। মাস্ক পরার জন্য পপলিনের ফিতা থাকতে হবে। একবার ব্যবহার করে সাবান-পানি দিয়ে ধুয়ে ও শুকিয়ে মাস্কটি পুনরায় ব্যবহার করা যাবে।\n" +
                        "\n" +
                        "আইইডিসিআরের ব্রিফিং : এক সপ্তাহ আগে দেশে যে তিনজনকে করোনাভাইরাসে আক্রান্ত হিসেবে শনাক্ত করা হয়েছিল, সুস্থ হয়ে ওঠায় তাদের একজন বাড়ি ফিরে গেছেন বলে জানিয়েছে রোগতত্ত্ব, রোগ নিয়ন্ত্রণ ও গবেষণা ইন্সটিটিউট।\n" +
                        "\n" +
                        "শুক্রবার মহাখালীর স্বাস্থ্য ভবনে অনুষ্ঠিত আইইডিসিআরের নিয়মিত ব্রিফিংয়ে ইন্সটিটিউটের পরিচালক অধ্যাপক মীরজাদী সেব্রিনা ফ্লোরা এ তথ্য জানান।\n" +
                        "\n" +
                        "তিনি বলেন, তিনজনের মধ্যে দু’জনের পরীক্ষার রিপোর্ট নেগেটিভ এসেছে। তাদের শরীরে এখন করোনাভাইরাস নেই। ২৪ ঘণ্টার ব্যবধানে দুটি পরীক্ষায় তাদের নমুনায় ভাইরাসের উপস্থিতি পাওয়া যায়নি। বিশ্ব স্বাস্থ্য সংস্থার প্রটোকল অনুসারে তারা এখন পুরোপুরি সুস্থ। আরেকজনের পারিবারিক সমস্যা থাকায় তিনি এখনও হাসপাতালে রয়েছেন। তবে তৃতীয় আক্রান্ত ব্যক্তির শরীরে এখনও ভাইরাসের সংক্রমণ রয়ে গেছে।\n" +
                        "\n" +
                        "অধ্যাপক ফ্লোরা বলেন, বাংলাদেশে নতুন করে আর কারও মধ্যে করোনাভাইরাস পাওয়া যায়নি। আইইডিসিআর সব প্রস্তুতিই নিয়ে রেখেছে। বর্তমানে বিভিন্ন হাসপাতালে মোট আটজনকে ‘আইসোলেশনে’ রাখা হয়েছে। গত ২৪ ঘণ্টায় আরও ২৪ জনের নমুনা পরীক্ষা করা হয়েছে। সব মিলিয়ে এ পর্যন্ত নমুনা পরীক্ষা করা হয়েছে মোট ১৮৭ জনের। তবে ওই তিনজন ছাড়া নতুন করে কারও শরীরে ভাইরাসের উপস্থিতি পাওয়া যায়নি।\n" +
                        "\n" +
                        "ভ্রমণ বাতিলের সিদ্ধান্ত সরকারের : বিশ্ব স্বাস্থ্য সংস্থার বাংলাদেশ প্রতিনিধি ডা. বর্ধন জাং রানা বলেছেন, ফ্লাইটে ভ্রমণ বাতিলের সিদ্ধান্তের এখতিয়ার বাংলাদেশ সরকারের। যখন সরকার মনে করবে করোনাভাইরাস সংক্রমণ রোধে ভ্রমণ বাতিল করা প্রয়োজন, তখন এ ঘোষণা দিতে পারে।\n" +
                        "\n" +
                        "শুক্রবার রাজধানীর মহাখালীর স্বাস্থ্য ভবনে করোনাভাইরাস পরিস্থিতি নিয়ে আলোচনায় তিনি এসব কথা বলেন।\n" +
                        "\n" +
                        "ডা. বর্ধন বলেন, সদস্যভুক্ত সব দেশকে ইন্টারন্যাশনাল হেলথ রেগুলেশনস (আইএইচআর) অনুসারে কী কারণে ভ্রমণ বাতিল করা হল তা বিশ্ব স্বাস্থ্য সংস্থাকে বিস্তারিত জানাতে হবে। তবে এই মুহূর্তে বাংলাদেশের ফ্লাইট বাতিলের কোনো প্রয়োজন নেই।\n" +
                        "\n" +
                        "তিনি বলেন, বর্তমান করোনাভাইরাস পরিস্থিতিতে যেসব দেশ ভ্রমণে বিধিনিষেধ আরোপ করেছে তাদের সবাইকে যুক্তিসঙ্গত কারণ লিখে জানাতে হচ্ছে।\n" +
                        "\n" +
                        "বাংলাদেশ রোগটি প্রতিরোধে বিভিন্ন কার্যক্রম গ্রহণ করছে উল্লেখ করে তিনি বলেন, এখন পর্যন্ত দেশে করোনাভাইরাসে আক্রান্ত রোগীর সংখ্যা কম। যারা ফ্লাইটে ভ্রমণ করছে তারা বিশ্ব স্বাস্থ্য সংস্থার ওয়েবসাইটে গিয়ে ভ্রমণ তথ্য পড়ে দেখতে পারেন, তাতে সিদ্ধান্ত গ্রহণে সুবিধা হবে।\n" +
                        "\n" +
                        "দেশব্যাপী হাসপাতালগুলোয় আইসোলেশন শয্যা প্রস্তুত : ঢাকা মহানগরীর ৬টি হাসপাতালে ৪০০ শয্যা, চট্টগ্রাম মহানগরীতে ২টি হাসপাতালে ১৫০ শয্যা, সিলেট মহানগরীতে ২টি হাসপাতালে ২০০ শয্যা, বরিশাল মহানগরীতে ২টি হাসপাতালে ৪০০ শয্যা এবং রংপুর মহানগরীতে ২টি হাসপাতালে ২০০ শয্যা কোভিড-১৯ সংক্রমিত ব্যক্তিদের আইসোলেশনের জন্য প্রস্তুত রাখা হয়েছে। দেশের ৫ বিভাগে ১৪টি হাসপাতালে ১৩৫০ শয্যা প্রস্তুত রাখা হয়েছে।\n" +
                        "\n" +
                        "কোভিড-১৯-এর উপসর্গ রয়েছে বা সন্দেহভাজনদের প্রতি নির্দেশনা : যেসব দেশে কোভিড-১৯-এর স্থানীয় সংক্রমণ ঘটেছে, সে দেশ থেকে আসা যাত্রীদের (দেশি-বিদেশি যে কোনো নাগরিক) ১৪ দিন স্বেচ্ছা কোয়ারেন্টিনে থাকার অনুরোধ জানিয়েছে স্বাস্থ্য অধিদফতর। তাদের কারও কোভিড-১৯-এর লক্ষণ দেখা গেলে অন্য কোথাও না গিয়ে আইইডিসিআরের হটলাইনে যোগাযোগ করার অনুরোধ করা হয়েছে।\n" +
                        "\n" +
                        "কোভিড-১৯ সংক্রান্ত তথ্য জানাতে স্বাস্থ্য অধিদফতরের স্থাপিত হটলাইনগুলো- স্বাস্থ্য বাতায়নের হটলাইন নম্বর : ১৬২৬৩, আইইডিসিআরের হটলাইন নম্বর : ০১৫৫০০৬৪৯০১-৫, ০১৪০১১৮৪৫৫১, ০১৪০১১৮৪৫৫৪, ০১৪০১১৮৪৫৫৫, ০১৪০১১৮৪৫৫৬, ০১৪০১১৮৪৫৫৯, ০১৪০১১৮৪৫৬০, ০১৪০১১৮৪৫৬৩, ০১৪০১১৮৪৫৬৮, ০১৯২৭৭১১৭৮৪, ০১৯২৭৭১১৭৮৫, ০১৯৩৭০০০০১১, ০১৯৩৭১১০০১১।\n" +
                        "\n" +
                        "স্বাস্থ্য অধিদফতর থেকে বলা হয়েছে, ইতিমধ্যে করোনাভাইরাস পৃথিবীর দুই-তৃতীয়াংশ দেশে স্থানীয় সংক্রমণ ঘটিয়েছে। বিশ্ব স্বাস্থ্য সংস্থা কোভিড-১৯-কে বিশ্ব মহামারী ঘোষণা করেছে। তাই যে দেশে আছেন, যেখানে আছেন সেখানেই অবস্থান করুন। আরোহণ, ট্রানজিট ও অবতরণের বিমান বন্দরসমূহের টার্মিনাল এবং বিমানের ভেতরে যে কোনো যাত্রী/ক্রু কোভিড-১৯ সংক্রমিত যাত্রী/ক্রু দ্বারা সংক্রমিত হতে পারেন।\n" +
                        "\n" +
                        "সংক্রমিত যাত্রী/ক্রু যে দেশে অবতরণ করবেন সে দেশে কোভিড-১৯ সংক্রমণ ছড়িয়ে পড়তে পারে। প্রথমেই সংক্রমণ ছড়াতে পারে পরিবারের সদস্যদের মাঝে। নিজেকে ও প্রিয়জনদের এ সংক্রমণ থেকে মুক্ত রাখতে অত্যাবশ্যকীয় নয়, এমন আন্তর্জাতিক ভ্রমণ থেকে বিরত থাকতে বলা হয়েছে।");
                break;
            case R.id.question8:
                title.setText("সহজে জীবানুনাশক বানানোর নিয়ম");
                contentTV.setText("হ্যান্ড স্যানিটাইজার জেল তৈরির পদ্ধতি\n" +
                        "\n" +
                        "উপকরণ\n" +
                        "\n" +
                        "ক) আইসোপ্রোপাইল অ্যালকোহল (স্যানিটাইজারের মিশ্রণটিতে কমপক্ষে ৬০ শতাংশ অ্যালকোহল থাকতে হবে, বেশি অ্যালকোহল ব্যবহার করাও ক্ষতিকর)\n" +
                        "\n" +
                        "খ) অ্যালোভেরা জেল\n" +
                        "\n" +
                        "গ) টি ট্রি অয়েল\n" +
                        "\n" +
                        "পদ্ধতি\n" +
                        "\n" +
                        "৩ ভাগ আইসোপ্রোপাইল অ্যালকোহল, ১ ভাগ অ্যালোভেরা জেলে মিশ্রিত করুন। খ) তাতে কয়েক ফোঁটা টি ট্রি অয়েল দিন। গ) ভালোভাবে উপাদানগুলো মিশিয়ে ব্যবহার করুন।\n" +
                        "\n" +
                        "হ্যান্ড স্যানিটাইজার স্প্রে (WHO দ্বারা প্রস্তাবিত)\n" +
                        "\n" +
                        "উপকরণ\n" +
                        "\n" +
                        "ক) আইসোপ্রোপাইল অ্যালকোহল\n" +
                        "\n" +
                        "খ) গ্লিসারল\n" +
                        "\n" +
                        "গ) হাইড্রোজেন পারঅক্সাইড\n" +
                        "\n" +
                        "ঘ) ডিস্টিল ওয়াটার\n" +
                        "\n" +
                        "ঙ) স্প্রে বোতল\n" +
                        "\n" +
                        "পদ্ধতি\n" +
                        "\n" +
                        "২ টেবিল চামচ গ্লিসারলের সঙ্গে অ্যালকোহলটি মিশ্রিত করুন। এরপর তাতে ১ টেবিল চামচ হাইড্রোজেন পারক্সাইড মেশান এবং ডিস্টিল ওয়াটার বা ফোটানো পানি দিন, ঠাণ্ডা করে দেবেন। এরপর, মিশ্রণটি স্প্রে বোতলের মধ্যে ঢালুন।\n" +
                        "\n" +
                        "যেভাবে হ্যান্ড স্যানিটাইজার ব্যবহার করবেন-\n" +
                        "\n" +
                        "ঘন ঘন এবং ভালো করে হাত ধোবেন।\n" +
                        "দোকান থেকে কেনা হ্যান্ড স্যানিটাইজারে ৬০ শতাংশের বেশি অ্যালকোহল রয়েছে কি না তা নিশ্চিত করুন।\n" +
                        "হ্যান্ড স্যানিটাইজার প্রয়োগ করার আগে আপনার হাত শুকিয়ে নিন।\n" +
                        "যা করবেন না\n" +
                        "\n" +
                        "চিটচিটে বা নোংরা হাতে স্যানিটাইজার ব্যবহার করবেন না।\n" +
                        "হ্যান্ড ওয়াশিং বা হ্যান্ড স্যানাইটাইজারের পাশাপাশি বেবি ওয়াইপসও সমান কাজ করবে, এটা আশা করবেন না।\n" +
                        "হাত না ধুয়ে আপনার চোখ, নাক বা মুখ স্পর্শ করবেন না।\n" +
                        "৬০ শতাংশ অ্যালকোহলের বেশি ব্যবহার করবেন না। ১০০ শতাংশ অ্যালকোহল ব্যবহার করলে  অ্যালকোহল খুব দ্রুত বাষ্প হয়ে যায়। তখন এটি আপনার ত্বকে খুব দ্রুত শুকিয়ে যাবে এবং জ্বালা-পোড়ায় পরিণত হবে।");
                break;
        }

        dialog.setCancelable(false);
        dialog.show();
    }

    public void aboutUs(View view) {
        startActivity(new Intent(FirstActivity.this,AboutUs.class));
    }

    public void imageShow(View view) {

        final Dialog dialog=new Dialog(FirstActivity.this);
        dialog.setContentView(R.layout.image_row);
        LinearLayout linearLayout=dialog.findViewById(R.id.linearLayout);

        switch (view.getId()){
            case R.id.korona1:
                linearLayout.setBackgroundResource(R.drawable.coronaone);
                break;
            case R.id.korona2:
                linearLayout.setBackgroundResource(R.drawable.korona2);
                break;
            case R.id.korona3:
                linearLayout.setBackgroundResource(R.drawable.korona3);
                break;
            case R.id.korona4:
                linearLayout.setBackgroundResource(R.drawable.korona4);
                break;
            case R.id.korona5:
                linearLayout.setBackgroundResource(R.drawable.korona5);
                break;
            case R.id.korona6:
                linearLayout.setBackgroundResource(R.drawable.korona6);
                break;
        }

        dialog.show();
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setMessage("Are You Sure To Close This Application?");
        alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FirstActivity.this.finish();
            }
        });
        alert.setNegativeButton("Cancel",null);
        alert.show();

    }
}
