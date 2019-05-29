package com.example.dell.domea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView rck;
    private List<User> mList;
    private Rck_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rck = findViewById(R.id.rck);
        rck.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new Rck_Adapter(this, mList);
        rck.setAdapter(mAdapter);
        jiexi();
        rck.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rck.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                rck.loadMoreComplete();
            }
        });
    }

    private void jiexi() {
        OKutils.getapi().getlist()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            final String string = responseBody.string();
                            final JSONObject jsonObject = new JSONObject(string);
                            final JSONArray results = jsonObject.optJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                final JSONObject object = results.optJSONObject(i);
                                final String url = object.optString("url");
                                final String desc = object.optString("desc");
                                final User user = new User();
                                user.setDesc(desc);
                                user.setUrl(url);
                                mList.add(user);
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
