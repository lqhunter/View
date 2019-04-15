package com.lq.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lq.view.R;


/**
 * author : lqhunter
 * date : 2019/4/12 0012
 * description :
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.InnerHolder> {

    private OnItemClickListener onItemClickListener = null;
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rlv_item_layout_animator, viewGroup, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
        innerHolder.itemView.setTag(i);
        innerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(i);
            }
        });
        innerHolder.setData();

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_in_set);
        innerHolder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class InnerHolder extends RecyclerView.ViewHolder {

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData() {


        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
