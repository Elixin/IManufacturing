package com.lenovo.manufacture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.pojo.Information;

import java.util.ArrayList;
import java.util.List;

public class InfomationAdapter extends RecyclerView.Adapter<InfomationAdapter.VH> {

    private List<Information.DataBean> informations = new ArrayList<>();

    public void setInformations(List<Information.DataBean> informations) {
        this.informations = informations;
    }

    @NonNull
    @Override
    public InfomationAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.infomationitem, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InfomationAdapter.VH holder, int position) {
        Information.DataBean dataBean = informations.get(position);
        holder.mTitle.setText(dataBean.getInformationName());
        holder.mContent.setText(dataBean.getWords());
    }

    @Override
    public int getItemCount() {
        return informations.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private ImageView mPictrue;
        private TextView mTitle;
        private TextView mContent;
        public VH(@NonNull View itemView) {
            super(itemView);
            mPictrue = (ImageView) itemView.findViewById(R.id.pictrue);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mContent = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
