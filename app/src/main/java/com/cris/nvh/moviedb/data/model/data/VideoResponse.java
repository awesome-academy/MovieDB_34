package com.cris.nvh.moviedb.data.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class VideoResponse implements Parcelable {
	@SerializedName("results")
	private List<Video> mVideos;

	protected VideoResponse(Parcel in) {
		mVideos = in.readArrayList(Video.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(mVideos);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {
		@Override
		public VideoResponse createFromParcel(Parcel in) {
			return new VideoResponse(in);
		}

		@Override
		public VideoResponse[] newArray(int size) {
			return new VideoResponse[size];
		}
	};

	public List<Video> getVideos() {
		return mVideos;
	}
}
