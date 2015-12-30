package com.tengyun.qiushibaike.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tengyun.qiushibaike.R;
import com.tengyun.qiushibaike.domain.QiushiBean;
import com.tengyun.qiushibaike.utils.CircleTransFormation;
import com.tengyun.qiushibaike.utils.UrlUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<QiushiBean> list = null;

    public ItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<QiushiBean>();
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list != null){
            Log.d("ItemAdapter", "getItem "+list.size());
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public void addAll(Collection<? extends QiushiBean> collection){
        Log.d("ItemAdapter", "addAll " + collection.size());
        list.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        QiushiBean item = list.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.content.setText(item.getContent());
        if (item.getUserName()!= null){
            viewHolder.userName.setText(item.getUserName());
            Picasso.with(context).load(UrlUtil.getIconURL(item.getUserId(), item.getUserIcon()))
                    .transform(new CircleTransFormation())
                    .into(viewHolder.userIcon);
        }else {
            viewHolder.userName.setText("无名");
            viewHolder.userIcon.setImageResource(R.mipmap.ic_launcher);
        }

        if (item.getImage() == null){
            viewHolder.image.setVisibility(View.GONE);
        }else {
            viewHolder.image.setVisibility(View.VISIBLE);

            viewHolder.image.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(UrlUtil.getImageUrl(context, item.getImage()))
                    .resize(parent.getWidth(), 0)             //设置尺寸，不可以小于0，不可以同时为0；若一个不为0，另一个为0，则0不生效，按宽高比
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.image);

        }
        if (item.getVideoUrl() != null){
            viewHolder.ivPlay.setVisibility(View.VISIBLE);
            viewHolder.content.setVisibility(View.GONE);
            viewHolder.image.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(item.getVideoUrl())
                    .resize(parent.getWidth(),0)
                    .placeholder(R.mipmap.ic_launcher2)
                    .error(R.mipmap.ic_launcher2)
                    .into(viewHolder.image);
        }else{
            viewHolder.content.setVisibility(View.VISIBLE);
            viewHolder.ivPlay.setVisibility(View.GONE);
        }
        return convertView;
    }



    private static class ViewHolder{

        ImageView userIcon;
        ImageView image;
        TextView userName;
        TextView content;
        ImageView ivPlay;

        public ViewHolder(View itemView) {
            userIcon = (ImageView) itemView.findViewById(R.id.iv_user_icon);
            image = (ImageView) itemView.findViewById(R.id.iv_image);
            userName = (TextView) itemView.findViewById(R.id.tv_user_name);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            ivPlay = (ImageView) itemView.findViewById(R.id.iv_play);
        }
    }
}
