package com.tengyun.qiushibaike.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tengyun.qiushibaike.R;
import com.tengyun.qiushibaike.adapters.ItemAdapter;
import com.tengyun.qiushibaike.domain.QiushiBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ContentListFragment extends ListFragment implements Callback {

    public static final String CODE_URL = "url";
    private Call mCall;
    private ItemAdapter adapter;
    private String mUrl;


    public static ContentListFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(CODE_URL, url);
        ContentListFragment fragment = new ContentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContentListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ItemAdapter(getActivity());
        mUrl = getArguments().getString(CODE_URL);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(mUrl).get().build();
        mCall = client.newCall(request);

        mCall.enqueue(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) view.findViewById(R.id.lv_fragment);
        //TextView tv_fragment = (TextView) view.findViewById(R.id.tv_fragment);

        //setListAdapter(adapter);
        listView.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onFailure(Request request, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(Response response) throws IOException {
        String s = response.body().string();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray("items");
            final List<QiushiBean> list = new ArrayList<QiushiBean>();
            for (int i = 0; i < array.length(); i++) {
                list.add(new QiushiBean(array.getJSONObject(i)));
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("ContentListFragment", "run "+list.size());
                    adapter.addAll(list);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
