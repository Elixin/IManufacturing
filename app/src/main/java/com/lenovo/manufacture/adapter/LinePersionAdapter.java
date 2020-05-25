package com.lenovo.manufacture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.pojo.People;
import com.lenovo.manufacture.pojo.ProductionLine;
import com.lenovo.manufacture.pojo.Stage;

import java.util.List;

public class LinePersionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //最上方的车辆类型view
    private static int CARTYPE=1;
    //生产环节
    private static int PRODUCTION_LINE=2;
    //员工标题
    private static int PERSIONTITLE=3;
    //员工
    private static int PERSION=4;

    private ProductionLine.DataBean production;
    private List<People.DataBean> people;
    private Stage.DataBean stage;

    public void setProduction(ProductionLine.DataBean production) {
        this.production = production;
    }

    public void setPeople(List<People.DataBean> people) {
        this.people = people;
    }

    public void setStage(Stage.DataBean stage) {
        this.stage = stage;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==CARTYPE){//添加汽车类型界面
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitem_1, parent, false);
            return new viewone(inflate);
        } else if (viewType == PRODUCTION_LINE) {//添加当前生产环节界面
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitem_2, parent, false);
            return new viewtwo(inflate);
        }else  if (viewType == PERSIONTITLE){//添加员工标题界面
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitem_3, parent, false);
            return new viewthree(inflate);
        }else {//其他布局使用员工布局
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitem_4, parent, false);
            return new viewfour(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (production!=null){
            if (holder instanceof viewone){
                //引用汽车类型的viewholder
                setCarType((viewone) holder);

            }else if (holder instanceof viewtwo){
                setprodutionline((viewtwo) holder);
            }else if (holder instanceof viewthree){
                //title只有一个字符串不做任何操作
            }else {
                setpersion((viewfour) holder,position-3);
            }
        }
    }

    private void setpersion(viewfour holder, int i) {
        if (people.size()>0){
            People.DataBean dataBean = people.get(i);
            holder.mName.setText(dataBean.getPeopleName());
            holder.mStation.setText(dataBean.getContent());
        }
    }

    private void setprodutionline(viewtwo holder) {
        holder.mNowproduction.setText(stage.getContent());
    }

    private void setCarType(viewone holder) {
        switch (production.getCarId()){
            case 1:
                holder.mCarimg.setImageResource(R.drawable.car001);
                holder.mCartype.setText("轿车汽车");
                break;
            case 2:
                holder.mCarimg.setImageResource(R.drawable.car002);
                holder.mCartype.setText("SUV汽车");
                break;
            case 3:
                holder.mCarimg.setImageResource(R.drawable.car003);
                holder.mCartype.setText("MPV汽车");
                break;

        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return CARTYPE;
            case 1:
                return PRODUCTION_LINE;
            case 2:
                return  PERSIONTITLE;
        }
        return PERSION;
    }

    @Override
    public int getItemCount() {
        if (people!=null){
            return 3+people.size();
        }else{
            return 3;
        }
    }

    class viewone extends RecyclerView.ViewHolder{
        private ImageView mCarimg;
        private TextView mCartype;
        public viewone(@NonNull View itemView) {
            super(itemView);
            mCarimg = (ImageView) itemView.findViewById(R.id.carimg);
            mCartype = (TextView) itemView.findViewById(R.id.cartype);

        }
    }
    class viewtwo extends RecyclerView.ViewHolder{
        private TextView mNowproduction;

        public viewtwo(@NonNull View itemView) {
            super(itemView);

            mNowproduction = (TextView) itemView.findViewById(R.id.nowproduction);

        }
    }
    class viewthree extends RecyclerView.ViewHolder{

        public viewthree(@NonNull View itemView) {
            super(itemView);
        }
    }
    class viewfour extends RecyclerView.ViewHolder{
        private ImageView mPersiontx;
        private TextView mName;
        private TextView mStation;
        public viewfour(@NonNull View itemView) {
            super(itemView);
            mPersiontx = (ImageView) itemView.findViewById(R.id.persiontx);
            mName = (TextView) itemView.findViewById(R.id.name);
            mStation = (TextView) itemView.findViewById(R.id.station);

        }
    }
}
