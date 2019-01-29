package com.example.superme198.fundamental;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false);
        return new MyHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        TimeTable op = new TimeTable(myHolder.tableLayout, myHolder.v.getContext().getApplicationContext());
        op.showDate();
        op.initViews();
    }


    //return the length of recyclerview
    @Override
    public int getItemCount() {
        return 5;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TableLayout tableLayout;
        private View v;
        MyHolder(View v){
            super(v);
            this.v = v;
            tableLayout = v.findViewById(R.id.time_table);
        }
    }


}
