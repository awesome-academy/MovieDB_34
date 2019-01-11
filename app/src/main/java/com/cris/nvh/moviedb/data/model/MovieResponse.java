package com.cris.nvh.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class MovieResponse implements Parcelable {
	@SerializedName("results")
	private List<Movie> mMovies;
	@SerializedName("total_pages")
	private int mTotalPage;
	@SerializedName("total_results")
	private int mTotalResult;

	protected MovieResponse(Parcel in) {
		mMovies = in.readArrayList(Movie.class.getClassLoader());
		mTotalPage = in.readInt();
		mTotalResult = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(mMovies);
		dest.writeInt(mTotalPage);
		dest.writeInt(mTotalResult);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
		@Override
		public MovieResponse createFromParcel(Parcel in) {
			return new MovieResponse(in);
		}

		@Override
		public MovieResponse[] newArray(int size) {
			return new MovieResponse[size];
		}
	};

	public List<Movie> getMovies() {
		return mMovies;
	}

	public int getTotalPage() {
		return mTotalPage;
	}

	public int getTotalResult() {
		return mTotalResult;
	}
}
