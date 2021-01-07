package com.mehedi.koronaviruscausesTusu.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mehedi.koronaviruscausesTusu.R;
import com.mehedi.koronaviruscausesTusu.Services.WorldAllDataServices;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryAdapter extends ArrayAdapter {

    private  List<WorldAllDataServices> worldAllDataServices;
    private Context context;


    public CountryAdapter(@NonNull Context context, List<WorldAllDataServices> worldAllDataServices) {
        super(context, R.layout.countree_row,worldAllDataServices);
        this.context=context;
        this.worldAllDataServices=worldAllDataServices;
    }

    class ViewHolder{
        ImageView countryIV;
        TextView countryTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.countree_row,parent,false);
            holder.countryIV=convertView.findViewById(R.id.countryIV);
            holder.countryTV=convertView.findViewById(R.id.countryTV);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(Uri.parse(worldAllDataServices.get(position).getCountryInfo().getFlag())).into(holder.countryIV);
        holder.countryTV.setText(worldAllDataServices.get(position).getCountry());


        return convertView;
    }
}
