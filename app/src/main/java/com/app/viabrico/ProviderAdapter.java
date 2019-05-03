package com.app.viabrico;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Log.i("Bigeard", listProvider.get(position).name);
        holder.providerName.setText(listProvider.get(position).name);
        holder.providerDescription.setText(listProvider.get(position).description);
        holder.providerAddress.setText(listProvider.get(position).address);
        holder.providerPhone.setText(listProvider.get(position).phone);
        holder.providerMail.setText(listProvider.get(position).mail);
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
    public class ProviderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView providerName = null;
        TextView providerDescription = null;
        TextView providerAddress = null;
        TextView providerPhone = null;
        TextView providerMail = null;
        Button providerEdit = null;
        Button providerDelete = null;

        ProviderViewHolder(final View itemView) {
            super(itemView);

            providerName = itemView.findViewById(R.id.provider_name);
            providerDescription = itemView.findViewById(R.id.provider_description);
            providerAddress = itemView.findViewById(R.id.provider_address);
            providerPhone = itemView.findViewById(R.id.provider_phone);
            providerMail = itemView.findViewById(R.id.provider_mail);
            providerEdit = itemView.findViewById(R.id.provider_edit);
            providerDelete = itemView.findViewById(R.id.provider_delete);

            // listener :
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Log.i("Bigeard", String.valueOf(getAdapterPosition()));
                    Provider clickProvider = listProvider.get(getAdapterPosition());
                    Log.i("Bigeard", String.valueOf(clickProvider));
                }
            });

            providerEdit.setOnClickListener(this);
            providerDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.provider_edit:
                    Provider provider_edit = getProviderPosition(this.getLayoutPosition());
                    Log.i("bigeard", provider_edit.name);
                    Intent intent = new Intent(view.getContext(), PutActivity.class);
                    intent.putExtra("provider", provider_edit);
                    view.getContext().startActivity(intent);
                    break;
                case R.id.provider_delete:
                    Provider provider_delete = getProviderPosition(this.getLayoutPosition());
                    AsyncT asyncT = new AsyncT("DELETE", "https://srm-viabrico.herokuapp.com/api/providers/" + provider_delete.id, null);
                    asyncT.execute();
                    Toast.makeText(ProviderActivity, "Delete Success", Toast.LENGTH_LONG).show();
                    ProviderActivity.getAll();
                    break;
                default:
                    break;
            }
        }

    }
}



