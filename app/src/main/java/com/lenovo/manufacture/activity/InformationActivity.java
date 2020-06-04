package com.lenovo.manufacture.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.pojo.Information;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class InformationActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private TextView content;
    private Information.DataBean collect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        EventBus.getDefault().register(this);
        initView();
    }
    @Subscribe(sticky = true)
    public void setdata(Information.DataBean collect){
        this.collect = collect;
    }

    private void initView() {
        StatusBarUtil.setColor(InformationActivity.this, Color.parseColor("#3E3E3E"));

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        title.setText(collect.getInformationName());
        content.setText(collect.getWords());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
