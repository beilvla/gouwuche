package com.example.gouwuche.utils.manager;


import com.example.gouwuche.Car.Bean.CC_Bean;
import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;
import com.example.gouwuche.utils.ApiService;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {

    @GET(ApiService.cx)
    Observable<gwc_Bean> getcall(@Query("uid") String uid);
    @GET(ApiService.gx)
    Observable<XG_Bean> getcall1(@Query("uid") String uid,@Query("sellerid") String sellerid,@Query("pid") String pid,@Query("selected") String selected,@Query("num") String num);
    @GET(ApiService.sc)
    Observable<CC_Bean> getcall2(@Query("uid") String uid,@Query("pid") String pid);
}

    //请求方式
    //@Part("img\"; filename=\\\"avatar.jpg") RequestBody file
    //http://api.tianapi.com/huabian/?key=71e58b5b2f930eaf1f937407acde08fe&num
//    @GET("book/search")
//    //Observable
//    //@Query() ?后面用它去拼接
//    //@QueryMap ?后面拼接数组用的
//   /* @Path：所有在网址中的参数（URL的问号前面），如：
//    http://102.10.10.132/api/Accounts/{accountId}
//    @Query：URL问号后面的参数，如：
//    http://102.10.10.132/api/Comments?access_token={access_token}
//    @QueryMap：相当于多个@Query
//    @Field：用于POST请求，提交单个数据
//    @Body：相当于多个@Field，以对象的形式提交
//*/
//
//    Observable<Book> getSearchBooks(@Query("q") String name,
//                                    @Query("tag") String tag, @Query("start") int start,
//                                    @Query("count") int count);

