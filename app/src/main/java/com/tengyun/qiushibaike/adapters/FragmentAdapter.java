package com.tengyun.qiushibaike.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tengyun.qiushibaike.fragments.ContentListFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<String> list;

    public FragmentAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    private String[] titles = new String[]{"专享","视频","纯文","纯图","精华"};

    @Override
    public Fragment getItem(int position) {
        return ContentListFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    //获取页的标题
    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
