package com.example.devarajakhil.inventory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DataObject dataObject;
    FloatingActionButton fab;
    EditText name,price,quantity;
    MyDB dbb = null;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private Context context = this;
    List<DataObject> data_list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // dataObject = new DataObject();

        dbb = new MyDB(this,null,null,1);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        data_list = dbb.getNotifications();

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(adapter);



        fab = (FloatingActionButton) findViewById(R.id.fabEdit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                name = (EditText) promptsView
                        .findViewById(R.id.editname);
                price = (EditText) promptsView
                        .findViewById(R.id.editprice);
                quantity = (EditText) promptsView
                        .findViewById(R.id.editquantity);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        addthis();
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
            }
        });
    }

    public void addthis(){
       // dbb = new MyDB(this,null,null,1);
       // dbb.addHandler(name.getText().toString(),price.getText().toString(), quantity.getText().toString());


        DataObject uobj = new DataObject();
        uobj.name = name.getText().toString();
        uobj.price = price.getText().toString();
        uobj.quantity = quantity.getText().toString();



        dbb.insertMessage(uobj);


        data_list = dbb.getNotifications();
        adapter.ReloadDatax(data_list);
        recyclerView.setAdapter(adapter);



        Toast.makeText(this, R.string.insertSuccesToast, Toast.LENGTH_SHORT).show();
    }
}
