package com.cris.nvh.moviedb.service;

import com.cris.nvh.moviedb.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cris.nvh.moviedb.util.Constant.BASE_URL;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDBClient {
    private static final String API_KEY = "api_key";
    private static final int WRITE_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 8000;
    private static MovieDBClient sClient;

    public static MovieDBClient getInstance() {
        if (sClient == null) {
            sClient = new MovieDBClient();
        }
        return sClient;
    }

    public Request initRetrofitRequest() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                HttpUrl httpUrl = request
                        .url()
                        .newBuilder()
                        .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                        .build();
                okhttp3.Request.Builder requestBuilder = request
                        .newBuilder()
                        .url(httpUrl);
                return chain.proceed(requestBuilder.build());
            }
        });
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Request.class);
    }
}
