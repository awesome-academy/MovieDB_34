package com.cris.nvh.moviedb.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cris.nvh.moviedb.util.Constant.BASE_URL;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieDBClient {
	private static final int WRITE_TIMEOUT = 5;
	private static final int READ_TIMEOUT = 5;
	private static final int CONNECT_TIMEOUT = 8;
	private static MovieDBClient sClient;
	private Request mRequest;

	private MovieDBClient() {
		mRequest = initRetrofitRequest();
	}

	public static MovieDBClient getInstance() {
		if (sClient == null) {
			sClient = new MovieDBClient();
		}
		return sClient;
	}

	public Request initRetrofitRequest() {
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.retryOnConnectionFailure(true)
				.build();
		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.client(httpClient).build();
		return retrofit.create(Request.class);
	}
}
