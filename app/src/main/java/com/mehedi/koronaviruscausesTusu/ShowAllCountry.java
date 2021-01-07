package com.mehedi.koronaviruscausesTusu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mehedi.koronaviruscausesTusu.Adapter.ShowAllCountryAdapter;
import com.mehedi.koronaviruscausesTusu.Responce.MyClickListener;
import com.mehedi.koronaviruscausesTusu.Services.WorldAllDataServices;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.mehedi.koronaviruscausesTusu.FirstActivity.conveterEngToBan;


public class ShowAllCountry extends AppCompatActivity implements MyClickListener {

    private ShowAllCountryAdapter allCountryAdapter;
    private List<WorldAllDataServices> worldAllDataServices;
    private RecyclerView recyclerView;


    private java.text.DecimalFormat format;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_country);


        format=new DecimalFormat("#,##,###");

        recyclerView=findViewById(R.id.recyclerViewshowAllCountry);
        worldAllDataServices=FirstActivity.worldAllDataServices;

        if (worldAllDataServices==null){
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
        }else {

            allCountryAdapter=new ShowAllCountryAdapter(this,worldAllDataServices,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(allCountryAdapter);


            String cases=getIntent().getStringExtra("causes");
            String deads=getIntent().getStringExtra("deads");
            String recorvered=getIntent().getStringExtra("recorvered");

            ((TextView)findViewById(R.id.ssTotalCasesTV)).setText(cases);
            ((TextView)findViewById(R.id.ssTotalDeadsTV)).setText(deads);
            ((TextView)findViewById(R.id.ssTotalRecorveredTV)).setText(recorvered);
        }


    }

    @Override
    public void ItemClickListener(WorldAllDataServices worldAllDataServices) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.list_row);

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(worldAllDataServices.getUpdated());
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
        Picasso.with(ShowAllCountry.this).load(Uri.parse(worldAllDataServices.getCountryInfo().getFlag()))
                .into(showImageV);

        showCountryTitle.setText(worldAllDataServices.getCountry());
        showCasesTV.setText(conveterEngToBan(worldAllDataServices.getCases()));
        showDeathsTV.setText(conveterEngToBan(worldAllDataServices.getDeaths()));
        showRecorveredTV.setText(conveterEngToBan(worldAllDataServices.getRecovered()));
        showActiveTV.setText(conveterEngToBan(worldAllDataServices.getActive()));
        showCricticalTV.setText(conveterEngToBan(worldAllDataServices.getCritical()));
        showCasesPOMTV.setText(format.format(worldAllDataServices.getCasesPerOneMillion()));
        showDeathsPOMTV.setText(format.format(worldAllDataServices.getDeathsPerOneMillion()));
        showBCasesTV.setText(conveterEngToBan(worldAllDataServices.getTodayCases()));
        showBDeathsTV.setText(conveterEngToBan(worldAllDataServices.getTodayDeaths()));

        Log.e("Item Click", "ItemClickListener: "+conveterEngToBan(worldAllDataServices.getCases()) );
        dialog.show();

    }
}
