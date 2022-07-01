package com.example.helperforbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavesAdapter extends RecyclerView.Adapter<SavesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Save> saves;

    SavesAdapter(Context context, List<Save> saves) {
        this.saves = saves;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SavesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavesAdapter.ViewHolder holder, int position)  {
        Save save = saves.get(position);
        holder.nameView.setText(save.getName());
        holder.needView.setText(save.getNeed());

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.delete(holder.getAdapterPosition());
                saves.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return saves.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, needView;
        final Button buttonDelete;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            needView = view.findViewById(R.id.need);
            buttonDelete = view.findViewById(R.id.delete);
        }
    }
}
