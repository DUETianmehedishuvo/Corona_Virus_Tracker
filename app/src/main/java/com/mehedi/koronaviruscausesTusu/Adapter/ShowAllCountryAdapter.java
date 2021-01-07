package com.mehedi.koronaviruscausesTusu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mehedi.koronaviruscausesTusu.R;
import com.mehedi.koronaviruscausesTusu.Responce.MyClickListener;
import com.mehedi.koronaviruscausesTusu.Services.WorldAllDataServices;

import java.text.DecimalFormat;
import java.util.List;

import static com.mehedi.koronaviruscausesTusu.FirstActivity.conveterEngToBan;


public class ShowAllCountryAdapter extends RecyclerView.Adapter<ShowAllCountryAdapter.MyViewHolder> {

    private Context context;
    private List<WorldAllDataServices> worldAllDataServices;
    private MyClickListener listener;
    
    private java.text.DecimalFormat format;

    public ShowAllCountryAdapter(Context context, List<WorldAllDataServices> worldAllDataServices,MyClickListener listener) {
        this.context = context;
        this.worldAllDataServices = worldAllDataServices;
        format=new DecimalFormat("#,##,###");
        this.listener=listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.show_all_country_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sCountryTV.setText(worldAllDataServices.get(position).getCountry());
        holder.sTotalRecorveredTV.setText(conveterEngToBan(worldAllDataServices.get(position).getRecovered()));
        holder.sTotalCasesTV.setText(conveterEngToBan(worldAllDataServices.get(position).getCases()));
        holder.sTotalDeads.setText(conveterEngToBan(worldAllDataServices.get(position).getDeaths()));

        if (worldAllDataServices.get(position).getTodayCases()>0){
            holder.sNewCases.setBackgroundColor(Color.parseColor("#424C94"));
            holder.sNewCases.setTextColor(Color.parseColor("#FFFFFF"));
            holder.sNewCases.setText(conveterEngToBan(worldAllDataServices.get(position).getTodayCases()));
        }else {
            holder.sNewCases.setBackgroundResource(R.drawable.main_screen_back4);
            holder.sNewCases.setTextColor(Color.parseColor("#424C94"));
            holder.sNewCases.setText(conveterEngToBan(0));
        }

        if (worldAllDataServices.get(position).getTodayDeaths()>0){
            holder.sNewDeads.setBackgroundColor(Color.parseColor("#E43A05"));
            holder.sNewDeads.setTextColor(Color.parseColor("#FFFFFF"));
            holder.sNewDeads.setText(conveterEngToBan(worldAllDataServices.get(position).getTodayDeaths()));
        }else {
            holder.sNewDeads.setBackgroundResource(R.drawable.main_screen_back5);
            holder.sNewDeads.setTextColor(Color.parseColor("#E43A05"));
            holder.sNewDeads.setText(conveterEngToBan(0));
        }


    }

    @Override
    public int getItemCount() {
        return worldAllDataServices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView sCountryTV,sTotalRecorveredTV,sTotalCasesTV,sTotalDeads,sNewCases,sNewDeads;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sCountryTV=itemView.findViewById(R.id.scountryTV);
            sTotalRecorveredTV=itemView.findViewById(R.id.sTotalRecorveredTV);
            sTotalCasesTV=itemView.findViewById(R.id.sTotalCausesTV);
            sTotalDeads=itemView.findViewById(R.id.sTotalDeadsTV);
            sNewCases=itemView.findViewById(R.id.sNewCasesTV);
            sNewDeads=itemView.findViewById(R.id.sNewDeadsTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClickListener(worldAllDataServices.get(getAdapterPosition()));
                }
            });
        }
    }
}
