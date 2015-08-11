package com.bongn.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.bongn.testapp.adapter.PhotoGridAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TestFresco extends Activity {

    @InjectView(R.id.gv_show_data)
    GridView gvShowData;

    private PhotoGridAdapter mAdapter;
    private String[] picArr = new String[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fresco);
        ButterKnife.inject(this);

        mAdapter = new PhotoGridAdapter(this);
        gvShowData.setAdapter(mAdapter);
        mAdapter.setPictureArr(picArr);
    }


}
