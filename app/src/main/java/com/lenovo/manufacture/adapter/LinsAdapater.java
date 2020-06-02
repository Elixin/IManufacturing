package com.lenovo.manufacture.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.pojo.UserProductionLine;

import java.util.ArrayList;
import java.util.List;

public class LinsAdapater extends RecyclerView.Adapter<LinsAdapater.VH> {

    private List<UserProductionLine.DataBean> userproduction = new ArrayList<>();

    public void setUserproduction(List<UserProductionLine.DataBean> userproduction) {
        this.userproduction = userproduction;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.linesview, parent, false);
        return new VH(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        UserProductionLine.DataBean dataBean = userproduction.get(position);
        switch (dataBean.getPosition()){
            case 0:
                holder.mLinename.setText("第一生产线");
                break;
            case 1:
                holder.mLinename.setText("第二生产线");
                break;
            case 2:
                holder.mLinename.setText("第三生产线");
                break;
            case 3:
                holder.mLinename.setText("第四生产线");
                break;
        }
        if (dataBean.getIsAI()==0){
            holder.mIsai.setText("等待操作");
            holder.mIsai.setTextColor(Color.parseColor("#FBD253"));
        }else {
            holder.mIsai.setText("自动生产中");
            holder.mIsai.setTextColor(Color.parseColor("#0C9502"));
        }
    }

    @Override
    public int getItemCount() {
        return userproduction.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        private TextView mLinename;
        private TextView mIsai;
        public VH(@NonNull View itemView) {
            super(itemView);
            mLinename = (TextView) itemView.findViewById(R.id.linename);
            mIsai = (TextView) itemView.findViewById(R.id.isai);
        }
    }
}
