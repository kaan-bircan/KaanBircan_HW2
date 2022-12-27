package com.ctis487.ifdatabasenotgiven;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomItemHolder> {
    private Context context;
    private ArrayList<Team> recyclerItemValues;

    DatabaseHelper dbHelper;


    public CustomRecyclerViewAdapter(Context context, ArrayList<Team> values){
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CustomItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false);

        CustomItemHolder mViewHolder = new CustomItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomItemHolder myRecyclerViewItemHolder, int i) {

        final Team contact = recyclerItemValues.get(i);
        int pos = i;

        myRecyclerViewItemHolder.tvName.setText(contact.getName());

        myRecyclerViewItemHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                //dialog
               displayDialog(contact);
            }
        });


    }
    public void refreshCustomAdapterAfterDelete(int position){
        recyclerItemValues.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

     class CustomItemHolder extends  RecyclerView.ViewHolder{
        TextView tvName, tvId;
        ImageButton btnDetail;
        ConstraintLayout parentLayout;
        public CustomItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvId = itemView.findViewById(R.id.tvItemName2);
            btnDetail = itemView.findViewById(R.id.btnItemDetail);
            parentLayout = itemView.findViewById(R.id.constParentLayout);
        }
    }


    Dialog dialog;

    public void displayDialog(Team team){
        dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.dialog);
        TextView tvDialogName = dialog.findViewById(R.id.tvItemName);
        Button btnDialogClose = dialog.findViewById(R.id.btnDialogClose);
        tvDialogName.setText(team.getName());
        btnDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
