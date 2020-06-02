package com.lenovo.manufacture.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
public class FInformationFragment extends BaseFragment {

    private TabLayout tab;
    private ViewPager infoview;
    private List<Fragment> fragments;
    private String[] typename = new String[]{"推荐", "资讯", "热点", "口碑"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_information, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tab = (TabLayout) view.findViewById(R.id.tab);
        initfragment();
        infoview = (ViewPager) view.findViewById(R.id.infoview);
        FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getFragmentManager()) {
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
        infoview.setAdapter(fragmentStatePagerAdapter);
        infoview.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(infoview));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(getContext(), "tab.getPosition()"+tab.getPosition(), Toast.LENGTH_SHORT).show();
                Shell shell = new Shell();
                shell.setTag(tab.getPosition());
                EventBus.getDefault().postSticky(shell);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Shell shell = new Shell();
        shell.setTag(0);
        EventBus.getDefault().postSticky(shell);
    }

    private void initfragment() {
        fragments = new ArrayList<>();
        fragments.add(new FinformationItemfFragment());
        fragments.add(new FinformationItemfFragment());
        fragments.add(new FinformationItemfFragment());
        fragments.add(new FinformationItemfFragment());

        for (int i = 0; i < fragments.size(); i++) {
            tab.addTab(tab.newTab());
            tab.getTabAt(i).setText(typename[i]);
        }
    }

}

class Shell{
    private  int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}