package com.example.covid24.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid24.R;
import com.example.covid24.model.datamodel.section.DeathsSection;
import com.example.covid24.model.datamodel.section.Section;
import com.example.covid24.model.datamodel.section.StatisticsSection;
import com.example.covid24.view.callback.OnSaveCountryClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SectionsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * This class is a hybrid recycler adapter, instead of holding a single layout
     * Statistical data regarding each country is divided into three main sections.
     * SECTIONS:
     * Statistics Section: Holds new cases statistics
     * Deaths Section: Holds new fatalities statistics
     * NoData Section: Holds a lottie animation when there is not available Covid records on that date.
     *
     * NOTE: This approach makes it easier to create and add new sections.
     */

    private List<Section> sectionsList;
    private Context context;
    private OnSaveCountryClickListener callBack;
    private static final int NO_DATA_SECTION = -1;
    private static final int STATISTICS_SECTION = 0;
    private static final int DEATHS_SECTION = 1;


    public SectionsRecyclerAdapter(Context context, List<Section> sectionList, OnSaveCountryClickListener callBack) {
        this.context = context;
        this.sectionsList = sectionList;
        this.callBack = callBack;
    }

    // This Method determines the Current Section Type (StatisticsSection/DeathsSection), and returns it back to inflate the appropriate Layout.
    @Override
    public int getItemViewType(int position) {
        Section currentModel = sectionsList.get(position);
        if (currentModel instanceof StatisticsSection) {
            return STATISTICS_SECTION;
        } else if (currentModel instanceof DeathsSection) {
            return DEATHS_SECTION;
        } else {
            return NO_DATA_SECTION;
        }
    }

    // This Method inflates the appropriate Layout depending on the Returned Section type.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == STATISTICS_SECTION) {
            view = LayoutInflater.from(this.context).inflate(R.layout.statistics_section, parent, false);
            return new StatisticsViewHolder(view);
        } else if (viewType == DEATHS_SECTION) {
            view = LayoutInflater.from(this.context).inflate(R.layout.death_section, parent, false);
            return new DeathsViewHolder(view);
        } else {
            view = LayoutInflater.from(this.context).inflate(R.layout.nodata_section, parent, false);
            return new NoDataViewHolder(view);
        }
    }

    //This Method binds the Views accordingly with its Section Type.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == STATISTICS_SECTION) {
            StatisticsViewHolder viewHolder = (StatisticsViewHolder) holder;
            StatisticsSection currentSection = (StatisticsSection) this.sectionsList.get(position);
            bindStatisticsSection(viewHolder, currentSection);
        } else if (getItemViewType(position) == DEATHS_SECTION) {
            DeathsViewHolder viewHolder = (DeathsViewHolder) holder;
            DeathsSection currentSection = (DeathsSection) this.sectionsList.get(position);
            bindDeathsSection(viewHolder, currentSection);
        }
    }

    private void bindStatisticsSection(StatisticsViewHolder viewHolder, StatisticsSection statisticsSection) {
        viewHolder.date.setText(statisticsSection.getDataDate());
        viewHolder.newCases.setText(statisticsSection.getNewCases());
        viewHolder.activeCases.setText(statisticsSection.getActiveCases());
        viewHolder.criticalCases.setText(statisticsSection.getCriticalCases());
        viewHolder.recoveredCases.setText(statisticsSection.getRecoveredCases());
        viewHolder.totalCases.setText(statisticsSection.getTotalCases());
        if (statisticsSection.getCountryName().equalsIgnoreCase("All")) {
            viewHolder.countryName.setText("World");
            viewHolder.isSavedStatus.setVisibility(View.GONE);
            viewHolder.countryFlagIcon.setVisibility(View.GONE);
        } else {
            viewHolder.countryName.setText(statisticsSection.getCountryName());
            if (statisticsSection.getCountry().isSaved())
                viewHolder.isSavedStatus.setImageResource(R.drawable.favorite_icon_24dp);
            Picasso.get()
                    .load(statisticsSection.getFlagURL())
                    .placeholder(R.drawable.globe_icon_150)
                    .into(viewHolder.countryFlagIcon);
        }
    }

    private void bindDeathsSection(DeathsViewHolder viewHolder, DeathsSection deathsSection) {
        viewHolder.newDeaths.setText(deathsSection.getNewDeaths());
        viewHolder.totalDeaths.setText(deathsSection.getTotalDeaths());
    }


    @Override
    public int getItemCount() {
        return sectionsList.size();
    }


    // Statistics Section Custom View Holder Class
    class StatisticsViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private TextView date;
        private TextView newCases, activeCases, criticalCases, recoveredCases, totalCases;
        private ImageView isSavedStatus;
        private ImageView countryFlagIcon;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.TV_countryName);
            date = itemView.findViewById(R.id.TV_date);
            newCases = itemView.findViewById(R.id.TV_newCases);
            activeCases = itemView.findViewById(R.id.TV_activeCases);
            criticalCases = itemView.findViewById(R.id.TV_criticalCases);
            recoveredCases = itemView.findViewById(R.id.TV_recoveredCases);
            totalCases = itemView.findViewById(R.id.TV_totalCases);

            isSavedStatus = itemView.findViewById(R.id.IMG_saveIcon);
            countryFlagIcon = itemView.findViewById(R.id.IMG_countryIcon);

            isSavedStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatisticsSection section = (StatisticsSection) sectionsList.get(getAdapterPosition());
                    boolean isSaved = section.getCountry().isSaved();
                    if (!isSaved) {
                        isSavedStatus.setImageResource(R.drawable.favorite_icon_24dp);
                        section.getCountry().updateSavedStatus();
                    } else {
                        isSavedStatus.setImageResource(R.drawable.favorite_icon_border_24dp);
                        section.getCountry().updateSavedStatus();
                    }
                    SectionsRecyclerAdapter.this.callBack.OnClick();
                }
            });
        }
    }

    // Deaths Section Custom View Holder Class
    class DeathsViewHolder extends RecyclerView.ViewHolder {

        private TextView newDeaths, totalDeaths;

        public DeathsViewHolder(@NonNull View itemView) {
            super(itemView);
            newDeaths = itemView.findViewById(R.id.TV_newDeaths);
            totalDeaths = itemView.findViewById(R.id.TV_totalDeaths);
        }
    }

    // Deaths Section Custom View Holder Class
    class NoDataViewHolder extends RecyclerView.ViewHolder {

        public NoDataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
