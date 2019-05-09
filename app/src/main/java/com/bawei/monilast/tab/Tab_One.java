package com.bawei.monilast.tab;

import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.bawei.monilast.R;
import com.bawei.monilast.adapter.ListAdapter;
import com.bawei.monilast.base.BaseFragment;
import com.bawei.monilast.bean.Bean;
import com.bawei.monilast.util.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Tab_One extends BaseFragment {
    private Banner banner;
    private PullToRefreshListView pullToRefreshListView;
    private String server_url = "http://v.juhe.cn/toutiao/index?type=shehui&key=29d7c07cb8f559fd98b0daa003a3eaf3";
    private List<Bean.ResultBean.DataBean> list = new ArrayList<>();
    private ListAdapter listAdapter;
    private int page = 1;
    @Override
    protected int layoutID() {
        return R.layout.tab_one;
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.banner);
        pullToRefreshListView = view.findViewById(R.id.pull_list);
        initPull();
    }

    private void initPull() {
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page = 1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                initData();
            }
        });

        listAdapter = new ListAdapter(getActivity(),list);
        pullToRefreshListView.setAdapter(listAdapter);

    }

    @Override
    protected void initData() {
        boolean time = NetUtil.getTime(getActivity());
        if(time){

            NetUtil.getInstance().getAsyncTask(server_url, new NetUtil.CallBaskTask() {
                @Override
                public void onError(int code, String msg) {

                }

                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(s, Bean.class);
                    if(page == 1){
                        list.clear();
                    }
                    list.addAll(bean.getResult().getData());
                    listAdapter.notifyDataSetChanged();
                    pullToRefreshListView.onRefreshComplete();
                    initPull();
                }
            });

        }else{
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
        }
    }

    @Override
    protected void initListener() {

    }
}
