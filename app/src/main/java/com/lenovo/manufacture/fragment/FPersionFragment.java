package com.lenovo.manufacture.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adapter.PersionAdapter;
import com.lenovo.manufacture.base.BaseFragment;
import com.lenovo.manufacture.pojo.People;
import com.lenovo.manufacture.pojo.UserPeople;
import com.lenovo.manufacture.util.RecycleviewItemClickListener;

import java.util.List;

/**
 * 呈现员工数据
 */
public class FPersionFragment extends BaseFragment {

    private ImageView menu;
    private RecyclerView persionrv;
    private AlertDialog alertDialog;
    private Boolean ISTRUE=true;
    private PersionAdapter persionAdapter;

    private Handler handler = new Handler();
    private List<People.DataBean> peoples;
    private List<UserPeople.DataBean> userpeoples;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_persion, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menu = (ImageView) view.findViewById(R.id.menu);
        persionrv = view.findViewById(R.id.persionrv);
        persionrv.setLayoutManager(new LinearLayoutManager(getContext()));
        persionAdapter = new PersionAdapter();
        persionrv.setAdapter(persionAdapter);
        persionrv.addOnItemTouchListener(new RecycleviewItemClickListener(getContext(), persionrv,
                new RecycleviewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                Toast.makeText(getContext(), "点一下"+postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {
//                Toast.makeText(getContext(), "长按一下"+postion, Toast.LENGTH_SHORT).show();
            }
        }));
        persionAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            if (ISTRUE==false){
            }else {
                alertDialog.dismiss();
            }
        }else {

        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        ISTRUE = false;
        initWait();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                ISTRUE = true;
                initdata();
                onFragmentVisibleChange(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void initdata() {
        Gson gson = new Gson();
        peoples = gson.fromJson(people, People.class).getData();
        userpeoples = gson.fromJson(userPeoplejson, UserPeople.class).getData();
        persionAdapter.setPeoples(peoples);
        persionAdapter.setUserpeoples(userpeoples);
        handler.post(() -> {
           persionAdapter.notifyDataSetChanged();
        });

    }

    /**
     * 初始化加载界面
     */
    private void initWait() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.await, null, false);
        builder.setView(inflate);
        alertDialog = builder.create();
        alertDialog.show();
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
    private String people = "\n" +
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
