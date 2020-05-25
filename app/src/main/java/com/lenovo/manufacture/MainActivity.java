package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.lenovo.manufacture.fragment.FInformationFragment;
import com.lenovo.manufacture.fragment.FLineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {

    private final String[] tabtext = new String[]{"生产线","工厂资讯","员工管理","其他"};
    private final int[] imggers = new int[]{R.drawable.tabline, R.drawable.tabnews, R.drawable.tabpeople, R.drawable.tabother};
    private ViewPager viewpager;
    private TabLayout tab;
    private List<Fragment> fragments;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#3E3E3E"));
//        StatusBarUtil.setTransparent(MainActivity.this,50f);
        initView();

    }


    private void initView() {

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tab = (TabLayout) findViewById(R.id.tab);
        initfragemt();
        inittab();
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            }
        };
        viewpager.setAdapter(fragmentStatePagerAdapter);

        settab(tab);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
    }
    private ImageView mTabicon;
    private TextView mTabtext;
    private void settab(TabLayout tab) {
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab1 = tab.newTab();
            View inflate = LayoutInflater.from(this).inflate(R.layout.tablayout, null, false);
            tab1.setCustomView(inflate);
            mTabicon = (ImageView) inflate.findViewById(R.id.tabicon);
            mTabicon.setImageResource(imggers[i]);
            mTabtext = (TextView) inflate.findViewById(R.id.tabtext);
            mTabtext.setText(tabtext[i]);
            tab.addTab(tab1);
        }

    }

    private void inittab() {

    }

    private void initfragemt() {
        fragments = new ArrayList<>();
        fragments.add(new FLineFragment());
        fragments.add(new FInformationFragment());
        fragments.add(new TextfrFragment(2));
        fragments.add(new TextfrFragment(3));

    }
}


