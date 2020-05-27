package com.lenovo.manufacture.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adapter.PersionAdapter;
import com.lenovo.manufacture.base.BaseFragment;
import com.lenovo.manufacture.util.RecycleviewItemClickListener;

/**
 * 呈现员工数据
 */
public class FPersionFragment extends BaseFragment {

    private ImageView menu;
    private RecyclerView persionrv;

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
        PersionAdapter persionAdapter = new PersionAdapter();

        persionrv.setAdapter(persionAdapter);
        persionrv.addOnItemTouchListener(new RecycleviewItemClickListener(getContext(), persionrv, new RecycleviewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getContext(), "点一下"+postion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(getContext(), "长按一下"+postion, Toast.LENGTH_SHORT).show();
            }
        }));
        persionAdapter.notifyDataSetChanged();
    }

}
