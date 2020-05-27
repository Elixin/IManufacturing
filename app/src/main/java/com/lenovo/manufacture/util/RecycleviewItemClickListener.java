package com.lenovo.manufacture.util;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleviewItemClickListener implements RecyclerView.OnItemTouchListener {
    public interface OnItemClickListener{
        void onItemClick(View view, int postion);
        void onItemLongClick(View view, int postion);
    }

    private OnItemClickListener onItemClickListener;
    private GestureDetector gestureDetector;

    public RecycleviewItemClickListener(Context context,final RecyclerView recyclerView,OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view!=null&&onItemClickListener!=null){
                    onItemClickListener.onItemLongClick(view,recyclerView.getChildAdapterPosition(view));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (view!=null&&onItemClickListener!=null&&gestureDetector.onTouchEvent(e)){
            onItemClickListener.onItemClick(view,rv.getChildAdapterPosition(view));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
