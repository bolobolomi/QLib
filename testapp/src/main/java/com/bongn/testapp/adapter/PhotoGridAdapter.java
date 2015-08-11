package com.bongn.testapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bongn.testapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Qiang on 15/8/11.
 */
public class PhotoGridAdapter extends BaseAdapter {

    private String[] pictureArr;
    private ViewHolder mHolder;
    private Context context;
    private LayoutInflater inflater;

    public PhotoGridAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setPictureArr(String[] pictureArr) {
        this.pictureArr = pictureArr;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pictureArr.length;
    }

    @Override
    public Object getItem(int i) {
        return pictureArr[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            mHolder = new ViewHolder(view);
            view = inflater.inflate(R.layout.item_photo, null);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        String url = pictureArr[i];
        mHolder.ivPhoto.setImageURI(Uri.parse(url));
        return view;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_photo.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.iv_photo)
        SimpleDraweeView ivPhoto;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
