package com.example.devarajakhil.inventory;

/**
 * Created by Devaraj Akhil on 08-02-2018.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    //public static final String EXTRA_MESSAGE  = "com.example.devarajakhil.attendance";
    private final Context context;
    private List<DataObject> my_data;

    public CustomAdapter(Context context, List<DataObject> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    public void ReloadDatax(List<DataObject> my_data)
    {
        this.my_data = my_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_card, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.name.setText(my_data.get(position).getName());
        holder.price.setText(my_data.get(position).getPrice());
        holder.quantity.setText(my_data.get(position).getQuantity());

        Log.d("response:", my_data.get(position).getName());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //my_data.get(position).setQuantity(my_data.get(position).getQuantity()+1);
                String some = my_data.get(position).getQuantity();
                int an = Integer.parseInt(some);
                an++;
                String what = String.valueOf(an);
                my_data.get(position).quantity = what;
                Log.v("checkkkkkk",what);
                holder.quantity.setText(my_data.get(position).getQuantity());
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //my_data.get(position).setQuantity(my_data.get(position).getQuantity()-1);
                String some = my_data.get(position).getQuantity();
                int an = Integer.parseInt(some);
                if(an>0)
                an--;
                String what = String.valueOf(an);
                my_data.get(position).quantity = what;
                Log.v("checkkkkkk",what);
                holder.quantity.setText(my_data.get(position).getQuantity());
            }
        });
        holder.plus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final EditText name;
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.incdecprompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                name = (EditText) promptsView
                        .findViewById(R.id.editval);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        int given = Integer.parseInt(name.getText().toString());
                                        int some = Integer.parseInt(my_data.get(position).getQuantity());
                                            my_data.get(position).quantity = String.valueOf(some+given);
                                            holder.quantity.setText(my_data.get(position).getQuantity());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
        holder.minus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final EditText name;
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.incdecprompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                name = (EditText) promptsView
                        .findViewById(R.id.editval);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        int given = Integer.parseInt(name.getText().toString());
                                        int some = Integer.parseInt(my_data.get(position).getQuantity());
                                        if(some>given+1)
                                            some-=given;
                                        my_data.get(position).quantity = String.valueOf(some);
                                        holder.quantity.setText(my_data.get(position).getQuantity());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return my_data.size();
      //  return  0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,quantity,price;
        public Button plus,minus;
        public ViewHolder(View itemView) {
            super(itemView);
            // description = (TextView) itemView.findViewById(R.id.description);
            name = (TextView) itemView.findViewById(R.id.namee);
            quantity = (TextView) itemView.findViewById(R.id.quanTextView);
            price = (TextView) itemView.findViewById(R.id.pricee);
            plus = (Button) itemView.findViewById(R.id.buttoninc);
            minus = (Button) itemView.findViewById(R.id.buttondec);
        }
    }
}
