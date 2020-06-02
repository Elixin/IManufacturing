package com.lenovo.manufacture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.pojo.People;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersionAdapter extends RecyclerView.Adapter<PersionAdapter.VH> {
    private List<People.DataBean> peoples = new ArrayList<>();

    public void setPeoples(List<People.DataBean> peoples) {
        this.peoples = peoples;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.persionrviten, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mName.setText(peoples.get(position).getPeopleName());
        holder.mContent.setText(peoples.get(position).getContent());
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
