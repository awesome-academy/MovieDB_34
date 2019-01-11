package com.cris.nvh.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class Credit implements Parcelable {
	@SerializedName("cast")
	private List<Cast> mCasts;

	protected Credit(Parcel in) {
		mCasts = in.readArrayList(Cast.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(mCasts);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Credit> CREATOR = new Creator<Credit>() {
		@Override
		public Credit createFromParcel(Parcel in) {
			return new Credit(in);
		}

		@Override
		public Credit[] newArray(int size) {
			return new Credit[size];
		}
	};

	public List<Cast> getCasts() {
		return mCasts;
	}
}
