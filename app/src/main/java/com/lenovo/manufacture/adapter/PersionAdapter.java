package com.lenovo.manufacture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;

public class PersionAdapter extends RecyclerView.Adapter<PersionAdapter.VH> {


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitem_1, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView mCarimg;
        private TextView mCartype;
        
        public VH(@NonNull View itemView) {
            super(itemView);
            mCarimg = (ImageView) itemView.findViewById(R.id.carimg);
            mCartype = (TextView) itemView.findViewById(R.id.cartype);
        }
    }
}
