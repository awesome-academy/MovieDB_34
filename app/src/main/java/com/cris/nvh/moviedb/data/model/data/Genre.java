package com.cris.nvh.moviedb.data.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class Genre implements Parcelable {
	@SerializedName("id")
	private int mId;
	@SerializedName("name")
	private String mName;

	public Genre(int id){
		mId = id;
	}

	protected Genre(Parcel in) {
		mId = in.readInt();
		mName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(mId);
		parcel.writeString(mName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Genre> CREATOR = new Creator<Genre>() {
		@Override
		public Genre createFromParcel(Parcel in) {
			return new Genre(in);
		}

		@Override
		public Genre[] newArray(int size) {
			return new Genre[size];
		}
	};

	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}
}
