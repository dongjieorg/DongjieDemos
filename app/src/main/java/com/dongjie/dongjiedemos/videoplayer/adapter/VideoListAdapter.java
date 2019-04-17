package com.dongjie.dongjiedemos.videoplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.videoplayer.constant.VideoConstant;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by dongjie
 */
public class VideoListAdapter extends BaseAdapter {

    Context context;
    public VideoListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return VideoConstant.videoUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_videoview, null);
            viewHolder.jcVideoPlayer = convertView.findViewById(R.id.videoplayer);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.jcVideoPlayer.setUp(VideoConstant.videoUrls[position], JCVideoPlayer.SCREEN_LAYOUT_LIST,
                VideoConstant.videoTitles[position]);
        Glide.with(convertView.getContext())
                .load(VideoConstant.videoThumbs[position])
                .into(viewHolder.jcVideoPlayer.thumbImageView);
        return convertView;
    }

    class ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;
    }
}
