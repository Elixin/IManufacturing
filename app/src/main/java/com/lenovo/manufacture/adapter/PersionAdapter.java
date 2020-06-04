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
import com.lenovo.manufacture.pojo.People;
import com.lenovo.manufacture.pojo.UserPeople;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersionAdapter extends RecyclerView.Adapter<PersionAdapter.VH> {
    private List<People.DataBean> peoples = new ArrayList<>();
    private List<UserPeople.DataBean> userpeoples = new ArrayList<>();

    public void setUserpeoples(List<UserPeople.DataBean> userpeoples) {
        this.userpeoples = userpeoples;
    }

    public void setPeoples(List<People.DataBean> peoples) {
        this.peoples = peoples;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.persionrviten, parent, false);
        return new VH(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mName.setText(peoples.get(position).getPeopleName());
        holder.mContent.setText(peoples.get(position).getContent());
        holder.mType.setText("未招聘");
        holder.mType.setTextColor(Color.RED);
        for (int i = 0; i < userpeoples.size(); i++) {
            if (userpeoples.get(i).getPeopleId()==peoples.get(position).getId()){
                holder.mType.setText("已录取");
                holder.mType.setTextColor(Color.GREEN);
            }
        }
    }


    @Override
    public int getItemCount() {
        return peoples.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private CircleImageView mHeadimg;
        private TextView mName;
        private TextView mContent;
        private TextView mType;

        public VH(@NonNull View itemView) {
            super(itemView);
            mHeadimg = (CircleImageView) itemView.findViewById(R.id.headimg);
            mName = (TextView) itemView.findViewById(R.id.name);
            mContent = (TextView) itemView.findViewById(R.id.content);
            mType = (TextView) itemView.findViewById(R.id.type);

        }
    }
}
