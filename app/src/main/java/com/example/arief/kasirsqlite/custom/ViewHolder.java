package com.example.arief.kasirsqlite.custom;

//import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arief.kasirsqlite.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public LinearLayout linearLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        linearLayout = itemView.findViewById(R.id.recycle_item);
    }
}
