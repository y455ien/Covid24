package com.example.covid24.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.view.callback.OnCountryClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.CountryViewHolder>
        implements Filterable {

    private List<Country> countriesList;
    private List<Country> countriesListFiltered;
    private Context context;
    private OnCountryClickListener callBack;


    public CountriesRecyclerAdapter(Context context, List<Country> countriesList, OnCountryClickListener callBack) {
        this.countriesList = countriesList;
        this.countriesListFiltered = countriesList;
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.country_card_view, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        final Country currentCountry = this.countriesListFiltered.get(position);
        holder.countryName.setText(currentCountry.getCountryName());
        Picasso.get()
                .load(currentCountry.getFlagURL())
                .placeholder(R.drawable.globe_icon_150)
                .into(holder.countryFlag);
    }

    @Override
    public int getItemCount() {
        return this.countriesListFiltered.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private ImageView countryFlag;
        private TextView countryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.IMG_countryFlag);
            countryName = itemView.findViewById(R.id.TV_countryName);
            // On click, notifies the listener (Countries Fragment) and passes the clicked country to the fragment.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.OnClick(countriesListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countriesListFiltered = countriesList;
                } else {
                    List<Country> filteredList = new ArrayList<>();
                    for (Country currentCountry : countriesList) {
                        // Name match condition.
                        if (currentCountry.getCountryName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(currentCountry);
                        }
                    }
                    countriesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countriesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countriesListFiltered = (List<Country>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
