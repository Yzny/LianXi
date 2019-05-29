package com.example.dell.domea;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by dell on 2019/5/29.
 */

public interface ApiService {
    public String Url="http://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<ResponseBody> getlist();
}
