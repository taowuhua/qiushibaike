package com.tengyun.qiushibaike;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.tengyun.qiushibaike.adapters.FragmentAdapter;
import com.tengyun.qiushibaike.utils.UrlUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private LinearLayout mLinearLayout;
    private ActionBarDrawerToggle toggle;
    private ViewPager mViewPager;
    List<String> datas;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        //模拟数据
       // for (int i = 0; i < 5; i++) {
            datas.add(UrlUtil.URL_HOT_SUGGEST);
            datas.add(UrlUtil.URL_ETLITE_VIDEO);
            datas.add(UrlUtil.URL_ETLITE_TEXT);
            datas.add(UrlUtil.URL_ETLITE_DAY);
            datas.add(UrlUtil.URL_HOT_LATEST);
        //}
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), datas));

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw);
        mNavigationView = (NavigationView) findViewById(R.id.menu);
        mLinearLayout = (LinearLayout) findViewById(R.id.content);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab);

        // 设置导航菜单的选中监听
        mNavigationView.setNavigationItemSelectedListener(this);

        //actionBar的显示Home
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        //三条横线的Menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        // 设置drawLayout的动作影响上面toggle的状态
        mDrawerLayout.setDrawerListener(toggle);

        datas = new ArrayList<String>();

        mNavigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);

    }

    /**
     * 监听menu的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //toggle控制DeawLayout
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
