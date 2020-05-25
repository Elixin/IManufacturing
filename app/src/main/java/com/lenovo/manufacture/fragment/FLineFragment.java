package com.lenovo.manufacture.fragment;


import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adapter.LinePersionAdapter;
import com.lenovo.manufacture.adapter.LinsAdapater;
import com.lenovo.manufacture.base.BaseFragment;
import com.lenovo.manufacture.pojo.Car;
import com.lenovo.manufacture.pojo.People;
import com.lenovo.manufacture.pojo.ProductionLine;
import com.lenovo.manufacture.pojo.Stage;
import com.lenovo.manufacture.pojo.UserPeople;
import com.lenovo.manufacture.pojo.UserProductionLine;

import java.util.ArrayList;
import java.util.List;

public class FLineFragment extends BaseFragment {

    private RecyclerView linev;
    private RecyclerView linepersion;
    private static final String TAG = "FLineFragment";
    private AlertDialog alertDialog=null;
    private LinsAdapater linsAdapater;
    private LinePersionAdapter persionAdapter;
    private Gson gson = new Gson();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_line, null);
    }

    private final Boolean TRUE = true;
    private final Boolean FALSE = false;
    private boolean mTF;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linev = (RecyclerView) view.findViewById(R.id.linev);
        linepersion = (RecyclerView) view.findViewById(R.id.linepersion);


        linsAdapater = new LinsAdapater();
        linev.setLayoutManager(new LinearLayoutManager(getContext()));
        linev.setAdapter(linsAdapater);
        persionAdapter = new LinePersionAdapter();
        linepersion.setLayoutManager(new LinearLayoutManager(getContext()));
        linepersion.setAdapter(persionAdapter);

    }

    private void initWait() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.await, null, false);
        builder.setView(inflate);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private Handler handler = new Handler();
    private void initdata() {
        //请求生产线信息
        List<ProductionLine.DataBean> production = gson.fromJson(productionlinejson, ProductionLine.class).getData();
        List<UserProductionLine.DataBean> userproduction = gson.fromJson(userproductionlinejson, UserProductionLine.class).getData();
        //请求车辆信息
        List<Car.DataBean> car = gson.fromJson(carjson, Car.class).getData();
        //请求员工信息
        List<UserPeople.DataBean> userpeople = gson.fromJson(userPeoplejson, UserPeople.class).getData();
        List<People.DataBean> people = gson.fromJson(peoplejson, People.class).getData();
        //请求当前生产环节信息
        List<Stage.DataBean> stage = gson.fromJson(stagejson, Stage.class).getData();
        handler.post(() -> {
            linsAdapater.setUserproduction(userproduction);
            linsAdapater.notifyDataSetChanged();
            setDataInAdapet(production, userproduction, userpeople, people, stage);
            persionAdapter.notifyDataSetChanged();
        });
    }

    private void setDataInAdapet(List<ProductionLine.DataBean> production, List<UserProductionLine.DataBean> userproduction, List<UserPeople.DataBean> userpeople, List<People.DataBean> people, List<Stage.DataBean> stage) {
        for (int i = 0; i < production.size(); i++) {
            if (userproduction.get(0).getProductionLineId() == production.get(i).getId()) {
                //将数据传入adapter
                persionAdapter.setProduction(production.get(i));
                break;
            }
        }
        List<People.DataBean> peoples = new ArrayList<>();
        for (int i = 0; i < userpeople.size(); i++) {
            if (userproduction.get(0).getId() == userpeople.get(i).getUserProductionLineId()) {
                Log.d(TAG, "setDataInAdapet: "+userpeople.get(i).getUserProductionLineId());
                for (int j = 0; j < people.size(); j++) {
                    if (userpeople.get(i).getPeopleId() == people.get(j).getId()) {
                        peoples.add(people.get(j));
                    }
                }
            }
        }
        persionAdapter.setPeople(peoples);
        for (int i = 0; i < stage.size(); i++) {
            if (stage.get(i).getId() == userproduction.get(0).getStageId()) {
                persionAdapter.setStage(stage.get(i));
                break;
            }
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            if (mTF==FALSE){
            }else {
                alertDialog.dismiss();
            }
        }else { }
    }

    @Override
    protected void onFragmentFirstVisible() {
        mTF = FALSE;
        Log.d(TAG, "onFragmentFirstVisible: 数据加载中");
        initWait();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                mTF = TRUE;
                initdata();
                onFragmentVisibleChange(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    String userPeoplejson = "\n" +
            "    {\n" +
            "        \"status\": 200,\n" +
            "        \"message\": \"SUCCESS\",\n" +
            "        \"data\": [\n" +
            "            {\n" +
            "                \"id\": 4381,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 52,\n" +
            "                \"peopleId\": 8,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": 4\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4380,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 52,\n" +
            "                \"peopleId\": 7,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": 3\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4379,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 52,\n" +
            "                \"peopleId\": 5,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": 1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4378,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 30,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4377,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 40,\n" +
            "                \"peopleId\": 23,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": 1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4376,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 25,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": 6\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4375,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 22,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4374,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 50,\n" +
            "                \"peopleId\": 17,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": 8\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4373,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 15,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4372,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 16,\n" +
            "                \"userProductionLineId\": 2655,\n" +
            "                \"workPostId\": 7\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4371,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 4,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4370,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 3,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4369,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 72,\n" +
            "                \"peopleId\": 2,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": 2\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4368,\n" +
            "                \"userWorkId\": 1,\n" +
            "                \"power\": 100,\n" +
            "                \"peopleId\": 1,\n" +
            "                \"userProductionLineId\": 2654,\n" +
            "                \"workPostId\": \"\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }";

    String userproductionlinejson = "\n" +
            "   {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 2655,\n" +
            "               \"userWorkId\": 1,\n" +
            "               \"stageId\": 13,\n" +
            "               \"productionLineId\": 2,\n" +
            "               \"type\": 0,\n" +
            "               \"position\": 3,\n" +
            "               \"isAI\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 2654,\n" +
            "               \"userWorkId\": 1,\n" +
            "               \"stageId\": 44,\n" +
            "               \"productionLineId\": 1,\n" +
            "               \"type\": 0,\n" +
            "               \"position\": 2,\n" +
            "               \"isAI\": 1\n" +
            "           }\n" +
            "       ]\n" +
            "   }\n" +
            "  \n" +
            "  ";
    String productionlinejson = " {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 1,\n" +
            "               \"productionLineName\": \"轿车车型生产线\",\n" +
            "               \"content\": \"生产轿车汽车\",\n" +
            "               \"carId\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 2,\n" +
            "               \"productionLineName\": \"MPV车型生产线\",\n" +
            "               \"content\": \"生产MPV汽车\",\n" +
            "               \"carId\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 3,\n" +
            "               \"productionLineName\": \"SUV车型生产线\",\n" +
            "               \"content\": \"生产SUV汽车\",\n" +
            "               \"carId\": 3\n" +
            "           }\n" +
            "       ]\n" +
            "   }";
    String stagejson = "\n" +
            "   {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 5,\n" +
            "               \"stageName\": \"MPV汽车放置底盘\",\n" +
            "               \"content\": \"将汽车底盘放置在作业线上\",\n" +
            "               \"plStepId\": 5,\n" +
            "               \"nextStageId\": 6\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 6,\n" +
            "               \"stageName\": \"MPV汽车左前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左前悬挂固定在汽车底盘左前部\",\n" +
            "               \"plStepId\": 6,\n" +
            "               \"nextStageId\": 7\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 7,\n" +
            "               \"stageName\": \"MPV汽车右前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右前悬挂固定在汽车底盘右前部\",\n" +
            "               \"plStepId\": 7,\n" +
            "               \"nextStageId\": 8\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 8,\n" +
            "               \"stageName\": \"MPV汽车左后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 8,\n" +
            "               \"nextStageId\": 9\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 9,\n" +
            "               \"stageName\": \"MPV汽车右后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 9,\n" +
            "               \"nextStageId\": 10\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 10,\n" +
            "               \"stageName\": \"MPV汽车固定车架\",\n" +
            "               \"content\": \"将汽车车架固定在底盘上\",\n" +
            "               \"plStepId\": 10,\n" +
            "               \"nextStageId\": 12\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 11,\n" +
            "               \"stageName\": \"MPV汽车发动机\",\n" +
            "               \"content\": \"将发动机放置进车架前部\",\n" +
            "               \"plStepId\": 12,\n" +
            "               \"nextStageId\": 13\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 12,\n" +
            "               \"stageName\": \"MPV汽车椅子\",\n" +
            "               \"content\": \"将前排座椅后排座椅固定在车内\",\n" +
            "               \"plStepId\": 11,\n" +
            "               \"nextStageId\": 11\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 13,\n" +
            "               \"stageName\": \"MPV汽车方向盘\",\n" +
            "               \"content\": \"将方向盘放入车内前部\",\n" +
            "               \"plStepId\": 13,\n" +
            "               \"nextStageId\": 14\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 14,\n" +
            "               \"stageName\": \"MPV汽车左前车门\",\n" +
            "               \"content\": \"将左前车门固定在车架上\",\n" +
            "               \"plStepId\": 14,\n" +
            "               \"nextStageId\": 15\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 15,\n" +
            "               \"stageName\": \"MPV汽车右前车门\",\n" +
            "               \"content\": \"将右前车门固定在车架上\",\n" +
            "               \"plStepId\": 15,\n" +
            "               \"nextStageId\": 16\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 16,\n" +
            "               \"stageName\": \"MPV汽车左后车门\",\n" +
            "               \"content\": \"将左后车门固定在车架上\",\n" +
            "               \"plStepId\": 16,\n" +
            "               \"nextStageId\": 17\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 17,\n" +
            "               \"stageName\": \"MPV汽车右后车门\",\n" +
            "               \"content\": \"将右后车门固定在车架上\",\n" +
            "               \"plStepId\": 17,\n" +
            "               \"nextStageId\": 18\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 18,\n" +
            "               \"stageName\": \"MPV汽车引擎盖\",\n" +
            "               \"content\": \"安装引擎盖\",\n" +
            "               \"plStepId\": 18,\n" +
            "               \"nextStageId\": 19\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 19,\n" +
            "               \"stageName\": \"MPV汽车前挡风玻璃\",\n" +
            "               \"content\": \"安装前挡风玻璃\",\n" +
            "               \"plStepId\": 19,\n" +
            "               \"nextStageId\": 20\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 20,\n" +
            "               \"stageName\": \"MPV汽车后挡风玻璃\",\n" +
            "               \"content\": \"安装后挡风玻璃\",\n" +
            "               \"plStepId\": 20,\n" +
            "               \"nextStageId\": 21\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 21,\n" +
            "               \"stageName\": \"MPV汽车左前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左前轮胎\",\n" +
            "               \"plStepId\": 21,\n" +
            "               \"nextStageId\": 22\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 22,\n" +
            "               \"stageName\": \"MPV汽车右前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右前轮胎\",\n" +
            "               \"plStepId\": 22,\n" +
            "               \"nextStageId\": 23\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 23,\n" +
            "               \"stageName\": \"MPV汽车左后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左后轮胎\",\n" +
            "               \"plStepId\": 23,\n" +
            "               \"nextStageId\": 24\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 24,\n" +
            "               \"stageName\": \"MPV汽车右后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右后轮胎\",\n" +
            "               \"plStepId\": 24,\n" +
            "               \"nextStageId\": null\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 25,\n" +
            "               \"stageName\": \"轿车汽车放置底盘\",\n" +
            "               \"content\": \"将汽车底盘放置在作业线上\",\n" +
            "               \"plStepId\": 25,\n" +
            "               \"nextStageId\": 26\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 26,\n" +
            "               \"stageName\": \"轿车汽车左前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左前悬挂固定在汽车底盘左前部\",\n" +
            "               \"plStepId\": 26,\n" +
            "               \"nextStageId\": 27\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 27,\n" +
            "               \"stageName\": \"轿车汽车右前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右前悬挂固定在汽车底盘右前部\",\n" +
            "               \"plStepId\": 27,\n" +
            "               \"nextStageId\": 28\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 28,\n" +
            "               \"stageName\": \"轿车汽车左后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 28,\n" +
            "               \"nextStageId\": 29\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 29,\n" +
            "               \"stageName\": \"轿车汽车右后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 29,\n" +
            "               \"nextStageId\": 30\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 30,\n" +
            "               \"stageName\": \"轿车汽车固定车架\",\n" +
            "               \"content\": \"将汽车车架固定在底盘上\",\n" +
            "               \"plStepId\": 30,\n" +
            "               \"nextStageId\": 32\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 31,\n" +
            "               \"stageName\": \"轿车汽车发动机\",\n" +
            "               \"content\": \"将发动机放置进车架前部\",\n" +
            "               \"plStepId\": 32,\n" +
            "               \"nextStageId\": 33\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 32,\n" +
            "               \"stageName\": \"轿车汽车椅子\",\n" +
            "               \"content\": \"将前排座椅后排座椅固定在车内\",\n" +
            "               \"plStepId\": 31,\n" +
            "               \"nextStageId\": 31\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 33,\n" +
            "               \"stageName\": \"轿车汽车方向盘\",\n" +
            "               \"content\": \"将方向盘放入车内前部\",\n" +
            "               \"plStepId\": 33,\n" +
            "               \"nextStageId\": 34\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 34,\n" +
            "               \"stageName\": \"轿车汽车左前车门\",\n" +
            "               \"content\": \"将左前车门固定在车架上\",\n" +
            "               \"plStepId\": 34,\n" +
            "               \"nextStageId\": 35\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 35,\n" +
            "               \"stageName\": \"轿车汽车右前车门\",\n" +
            "               \"content\": \"将右前车门固定在车架上\",\n" +
            "               \"plStepId\": 35,\n" +
            "               \"nextStageId\": 36\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 36,\n" +
            "               \"stageName\": \"轿车汽车左后车门\",\n" +
            "               \"content\": \"将左后车门固定在车架上\",\n" +
            "               \"plStepId\": 36,\n" +
            "               \"nextStageId\": 37\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 37,\n" +
            "               \"stageName\": \"轿车汽车右后车门\",\n" +
            "               \"content\": \"将右后车门固定在车架上\",\n" +
            "               \"plStepId\": 37,\n" +
            "               \"nextStageId\": 38\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 38,\n" +
            "               \"stageName\": \"轿车汽车引擎盖\",\n" +
            "               \"content\": \"安装引擎盖\",\n" +
            "               \"plStepId\": 38,\n" +
            "               \"nextStageId\": 39\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 39,\n" +
            "               \"stageName\": \"轿车汽车前挡风玻璃\",\n" +
            "               \"content\": \"安装前挡风玻璃\",\n" +
            "               \"plStepId\": 39,\n" +
            "               \"nextStageId\": 40\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 40,\n" +
            "               \"stageName\": \"轿车汽车后挡风玻璃\",\n" +
            "               \"content\": \"安装后挡风玻璃\",\n" +
            "               \"plStepId\": 40,\n" +
            "               \"nextStageId\": 41\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 41,\n" +
            "               \"stageName\": \"轿车汽车左前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左前轮胎\",\n" +
            "               \"plStepId\": 41,\n" +
            "               \"nextStageId\": 42\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 42,\n" +
            "               \"stageName\": \"轿车汽车右前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右前轮胎\",\n" +
            "               \"plStepId\": 42,\n" +
            "               \"nextStageId\": 43\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 43,\n" +
            "               \"stageName\": \"轿车汽车左后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左后轮胎\",\n" +
            "               \"plStepId\": 43,\n" +
            "               \"nextStageId\": 44\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 44,\n" +
            "               \"stageName\": \"轿车汽车右后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右后轮胎\",\n" +
            "               \"plStepId\": 44,\n" +
            "               \"nextStageId\": null\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 45,\n" +
            "               \"stageName\": \"SUV汽车放置底盘\",\n" +
            "               \"content\": \"将汽车底盘放置在作业线上\",\n" +
            "               \"plStepId\": 45,\n" +
            "               \"nextStageId\": 46\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 46,\n" +
            "               \"stageName\": \"SUV汽车左前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左前悬挂固定在汽车底盘左前部\",\n" +
            "               \"plStepId\": 46,\n" +
            "               \"nextStageId\": 47\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 47,\n" +
            "               \"stageName\": \"SUV汽车右前悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右前悬挂固定在汽车底盘右前部\",\n" +
            "               \"plStepId\": 47,\n" +
            "               \"nextStageId\": 48\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 48,\n" +
            "               \"stageName\": \"SUV汽车左后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车左后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 48,\n" +
            "               \"nextStageId\": 49\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 49,\n" +
            "               \"stageName\": \"SUV汽车右后悬挂\",\n" +
            "               \"content\": \"机械臂将汽车右后悬挂固定在汽车底盘左后部\",\n" +
            "               \"plStepId\": 49,\n" +
            "               \"nextStageId\": 50\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 50,\n" +
            "               \"stageName\": \"SUV汽车固定车架\",\n" +
            "               \"content\": \"将汽车车架固定在底盘上\",\n" +
            "               \"plStepId\": 50,\n" +
            "               \"nextStageId\": 52\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 51,\n" +
            "               \"stageName\": \"SUV汽车发动机\",\n" +
            "               \"content\": \"将发动机放置进车架前部\",\n" +
            "               \"plStepId\": 52,\n" +
            "               \"nextStageId\": 53\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 52,\n" +
            "               \"stageName\": \"SUV汽车椅子\",\n" +
            "               \"content\": \"将前排座椅后排座椅固定在车内\",\n" +
            "               \"plStepId\": 51,\n" +
            "               \"nextStageId\": 51\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 53,\n" +
            "               \"stageName\": \"SUV汽车方向盘\",\n" +
            "               \"content\": \"将方向盘放入车内前部\",\n" +
            "               \"plStepId\": 53,\n" +
            "               \"nextStageId\": 54\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 54,\n" +
            "               \"stageName\": \"SUV汽车左前车门\",\n" +
            "               \"content\": \"将左前车门固定在车架上\",\n" +
            "               \"plStepId\": 54,\n" +
            "               \"nextStageId\": 55\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 55,\n" +
            "               \"stageName\": \"SUV汽车右前车门\",\n" +
            "               \"content\": \"将右前车门固定在车架上\",\n" +
            "               \"plStepId\": 55,\n" +
            "               \"nextStageId\": 56\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 56,\n" +
            "               \"stageName\": \"SUV汽车左后车门\",\n" +
            "               \"content\": \"将左后车门固定在车架上\",\n" +
            "               \"plStepId\": 56,\n" +
            "               \"nextStageId\": 57\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 57,\n" +
            "               \"stageName\": \"SUV汽车右后车门\",\n" +
            "               \"content\": \"将右后车门固定在车架上\",\n" +
            "               \"plStepId\": 57,\n" +
            "               \"nextStageId\": 58\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 58,\n" +
            "               \"stageName\": \"SUV汽车引擎盖\",\n" +
            "               \"content\": \"安装引擎盖\",\n" +
            "               \"plStepId\": 58,\n" +
            "               \"nextStageId\": 59\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 59,\n" +
            "               \"stageName\": \"SUV汽车前挡风玻璃\",\n" +
            "               \"content\": \"安装前挡风玻璃\",\n" +
            "               \"plStepId\": 59,\n" +
            "               \"nextStageId\": 60\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 60,\n" +
            "               \"stageName\": \"SUV汽车后挡风玻璃\",\n" +
            "               \"content\": \"安装后挡风玻璃\",\n" +
            "               \"plStepId\": 60,\n" +
            "               \"nextStageId\": 61\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 61,\n" +
            "               \"stageName\": \"SUV汽车左前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左前轮胎\",\n" +
            "               \"plStepId\": 61,\n" +
            "               \"nextStageId\": 62\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 62,\n" +
            "               \"stageName\": \"SUV汽车右前轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右前轮胎\",\n" +
            "               \"plStepId\": 62,\n" +
            "               \"nextStageId\": 63\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 63,\n" +
            "               \"stageName\": \"SUV汽车左后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装左后轮胎\",\n" +
            "               \"plStepId\": 63,\n" +
            "               \"nextStageId\": 64\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 64,\n" +
            "               \"stageName\": \"SUV汽车右后轮胎\",\n" +
            "               \"content\": \"机械臂在底盘上安装右后轮胎\",\n" +
            "               \"plStepId\": 64,\n" +
            "               \"nextStageId\": null\n" +
            "           }\n" +
            "       ]\n" +
            "   }";
    String carjson = "\n" +
            "   {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 1,\n" +
            "               \"carName\": \"轿车汽车\",\n" +
            "               \"content\": \"轿车汽车标准型\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 2,\n" +
            "               \"carName\": \"MPV汽车\",\n" +
            "               \"content\": \"MPV汽车标准型\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 3,\n" +
            "               \"carName\": \"SUV汽车\",\n" +
            "               \"content\": \"SUV汽车标准型\"\n" +
            "           }\n" +
            "       ]\n" +
            "   }\n" +
            "  ";
    String peoplejson = "\n" +
            "   {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 1,\n" +
            "               \"peopleName\": \"李刚\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 200,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 2,\n" +
            "               \"peopleName\": \"丁运生\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 50,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 3,\n" +
            "               \"peopleName\": \"方华高\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 300,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 30,\n" +
            "               \"peopleName\": \"朱云贵\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 789,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 5,\n" +
            "               \"peopleName\": \"邹辉\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 150,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 6,\n" +
            "               \"peopleName\": \"杨文\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 80,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 7,\n" +
            "               \"peopleName\": \"朱元元\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 200,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 8,\n" +
            "               \"peopleName\": \"周正发\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 140,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 9,\n" +
            "               \"peopleName\": \"张伟\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 300,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 10,\n" +
            "               \"peopleName\": \"周丽\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 60,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 12,\n" +
            "               \"peopleName\": \"陈天云\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 200,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 11,\n" +
            "               \"peopleName\": \"陈敏\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 140,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 13,\n" +
            "               \"peopleName\": \"王百年\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 300,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 14,\n" +
            "               \"peopleName\": \"王莉\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 90,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 15,\n" +
            "               \"peopleName\": \"杨保俊\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 400,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 16,\n" +
            "               \"peopleName\": \"张大伟\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 120,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 17,\n" +
            "               \"peopleName\": \"徐超\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 351,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 18,\n" +
            "               \"peopleName\": \"于少明\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 130,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 19,\n" +
            "               \"peopleName\": \"吴雪平\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 456,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 22,\n" +
            "               \"peopleName\": \"崔鹏\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 123,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 23,\n" +
            "               \"peopleName\": \"David\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 145,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 24,\n" +
            "               \"peopleName\": \"张先龙\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 457,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 25,\n" +
            "               \"peopleName\": \"邓宁\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 999,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 26,\n" +
            "               \"peopleName\": \"钟华国\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 489,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车厂工人\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 27,\n" +
            "               \"peopleName\": \"罗梅\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 888,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工厂技术人员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 29,\n" +
            "               \"peopleName\": \"张锋\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 666,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 28,\n" +
            "               \"peopleName\": \"王琪\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 777,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 32,\n" +
            "               \"peopleName\": \"李芳\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 459,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 31,\n" +
            "               \"peopleName\": \"李冰\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 479,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"技术\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 4,\n" +
            "               \"peopleName\": \"省均\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 150,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 21,\n" +
            "               \"peopleName\": \"张旭\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 458,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车工程师\"\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 20,\n" +
            "               \"peopleName\": \"杨庆春\",\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3,\n" +
            "               \"talentMarketId\": 1,\n" +
            "               \"gold\": 365,\n" +
            "               \"hp\": 100,\n" +
            "               \"content\": \"汽车质检员\"\n" +
            "           }\n" +
            "       ]\n" +
            "   }\n";
}
