package com.mehedi.koronaviruscausesTusu.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mehedi.koronaviruscausesTusu.R;
import com.mehedi.koronaviruscausesTusu.Services.DayByDayService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DayByDayAdapter extends RecyclerView.Adapter<DayByDayAdapter.MyViewHolder> {

    private Context context;
    private List<DayByDayService> dayByDayServices;

    public DayByDayAdapter(Context context, List<DayByDayService> dayByDayServices) {
        this.context = context;
        this.dayByDayServices = dayByDayServices;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.condition_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.dateTV.setText(dayByDayServices.get(position).getDate());
        if (position==0){
            holder.casesTV.setText(String.valueOf(dayByDayServices.get(position).getConfirmed()));
            holder.recorveredTV.setText(String.valueOf(dayByDayServices.get(position).getRecovered()));
            holder.deadsTV.setText(String.valueOf(dayByDayServices.get(position).getDeaths()));
        }else {
            long casespre=dayByDayServices.get(position-1).getConfirmed();
            long casesnow=dayByDayServices.get(position).getConfirmed();

            long recorveredpre=dayByDayServices.get(position-1).getRecovered();
            long recorverednow=dayByDayServices.get(position).getRecovered();

            long deadspre=dayByDayServices.get(position-1).getDeaths();
            long deadsnow=dayByDayServices.get(position).getDeaths();

            long cases=casesnow-casespre;
            long recorvered=recorverednow-recorveredpre;
            long deads=deadsnow-deadspre;

            if (cases>0||recorvered>0||deads>0){
                holder.casesTV.setText(String.valueOf(cases));
                holder.recorveredTV.setText(String.valueOf(recorvered));
                holder.deadsTV.setText(String.valueOf(deads));
            }else {
                holder.dateTV.setVisibility(View.GONE);
                holder.casesTV.setVisibility(View.GONE);
                holder.recorveredTV.setVisibility(View.GONE);
                holder.deadsTV.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dayByDayServices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView dateTV,casesTV,recorveredTV,deadsTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV=itemView.findViewById(R.id.dayDateTV);
            casesTV=itemView.findViewById(R.id.dayCasesTV);
            recorveredTV=itemView.findViewById(R.id.dayRecorveredTV);
            deadsTV=itemView.findViewById(R.id.dayDeadsTV);
        }
    }
}
