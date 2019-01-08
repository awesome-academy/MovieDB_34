package com.cris.nvh.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class GenreResponse implements Parcelable {
	@SerializedName("genres")
	private List<Genre> mGenres;

	protected GenreResponse(Parcel in) {
		mGenres = in.readArrayList(Genre.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(mGenres);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<GenreResponse> CREATOR = new Creator<GenreResponse>() {
		@Override
		public GenreResponse createFromParcel(Parcel in) {
			return new GenreResponse(in);
		}

		@Override
		public GenreResponse[] newArray(int size) {
			return new GenreResponse[size];
		}
	};

	public List<Genre> getGenres() {
		return mGenres;
	}
}
