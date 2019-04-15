package com.lq.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lq.view.R;


/**
 * author : lqhunter
 * date : 2019/4/13 0013
 * description :
 */
public class ViewPagerItemFragment extends Fragment {

    public static ViewPagerItemFragment newIntence(String item) {
        ViewPagerItemFragment itemFragment = new ViewPagerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", item);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        TextView textView = view.findViewById(R.id.content_text);
        textView.setText(getArguments().get("title").toString());
        return view;
    }
}
