package com.app.viabrico;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder>
{

    // Activité :
    private ProviderActivity ProviderActivity = null;

    // list d'objets métier :
    private List<Provider> listProvider = null;

    /**
     * Constructeur.
     * @param ProviderActivity ProviderActivity
     * @param listProvider list de provider
     */
    public ProviderAdapter(ProviderActivity ProviderActivity, List<Provider> listProvider)
    {
        this.ProviderActivity = ProviderActivity;
        this.listProvider = listProvider;
    }

    @Override
    public ProviderViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewVehicle = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provider, parent, false);
        return new ProviderViewHolder(viewVehicle);
    }

    @Override
    public void onBindViewHolder(ProviderViewHolder holder, int position)
    {
      //  holder.providerName.setText(listProvider.get(position).name);
     //   holder.providerDescription.setText(listProvider.get(position).description);
    }

    @Override
    public int getItemCount()
    {
        return listProvider.size();
    }

    /**
     * Ajout d'un mémo à la list.
     * @param listVehicle list de Vehicle
     */
    public void actualiserProviders(List<Provider> listVehicle)
    {
        this.listProvider = listVehicle;
        notifyDataSetChanged();
    }

    /**
     * Retourne le Provider à la position indiquée.
     * @param position Position dans la list
     * @return Provider
     */
    public Provider getProviderPosition(int position)
    {
        return listProvider.get(position);
    }


    /**
     * ViewHolder.
     */
    class ProviderViewHolder extends RecyclerView.ViewHolder
    {

        TextView providerName = null;
        TextView providerDescription = null;
        TextView providerAddress = null;
        TextView providerPhone = null;
        TextView providerMail = null;

        ProviderViewHolder(final View itemView) {
            super(itemView);

           // providerName = itemView.findViewById(R.id.provider_name);
           // providerDescription = itemView.findViewById(R.id.provider_description);
           // providerAddress = itemView.findViewById(R.id.provider_address);
           // providerPhone = itemView.findViewById(R.id.provider_phone);
           // providerMail = itemView.findViewById(R.id.provider_mail);

            // listener :
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.i("Bigeard", String.valueOf(getAdapterPosition()));
                    Provider clickProvider = listProvider.get(getAdapterPosition());
                    Log.i("Bigeard", String.valueOf(clickProvider));
                  //  ProviderActivity.onClickItem(clickProvider);
                }
            });
        }
    }
}