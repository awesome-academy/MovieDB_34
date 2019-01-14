package com.cris.nvh.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class Movie implements Parcelable {
	@SerializedName("backdrop_path")
	private String mBackdropPath;
	@SerializedName("genres")
	private List<Genre> mGenres;
	@SerializedName("videos")
	private VideoResponse mVideoResponse;
	@SerializedName("production_companies")
	private List<Company> mCompanies;
	@SerializedName("credits")
	private Credit mCredits;
	@SerializedName("id")
	private int mId;
	@SerializedName("overview")
	private String mOverview;
	@SerializedName("poster_path")
	private String mPosterPath;
	@SerializedName("release_date")
	private String mReleaseDate;
	@SerializedName("title")
	private String mTitle;
	@SerializedName("status")
	private String mStatus;
	@SerializedName("vote_average")
	private double mVoteAverage;
	@SerializedName("vote_count")
	private int mVoteCount;

	public Movie(){
    }

	public Movie(int id) {
		mId = id;
	}

	protected Movie(Parcel in) {
		mBackdropPath = in.readString();
		mGenres = in.readArrayList(Genre.class.getClassLoader());
		mVideoResponse = in.readParcelable(VideoResponse.class.getClassLoader());
		mCompanies = in.readArrayList(Company.class.getClassLoader());
		mCredits = in.readParcelable(Credit.class.getClassLoader());
		mId = in.readInt();
		mOverview = in.readString();
		mPosterPath = in.readString();
		mReleaseDate = in.readString();
		mTitle = in.readString();
		mStatus = in.readString();
		mVoteAverage = in.readDouble();
		mVoteCount = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(mBackdropPath);
		parcel.writeList(mGenres);
		parcel.writeParcelable(mVideoResponse, 0);
		parcel.writeList(mCompanies);
		parcel.writeParcelable(mCredits, 0);
		parcel.writeInt(mId);
		parcel.writeString(mOverview);
		parcel.writeString(mPosterPath);
		parcel.writeString(mReleaseDate);
		parcel.writeString(mTitle);
		parcel.writeString(mStatus);
		parcel.writeDouble(mVoteAverage);
		parcel.writeInt(mVoteCount);

	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Movie> CREATOR = new Creator<Movie>() {
		@Override
		public Movie createFromParcel(Parcel in) {
			return new Movie(in);
		}

		@Override
		public Movie[] newArray(int size) {
			return new Movie[size];
		}
	};

	public String getBackdropPath() {
		return mBackdropPath;
	}

	public List<Genre> getGenres() {
		return mGenres;
	}

	public VideoResponse getVideoResponse() {
		return mVideoResponse;
	}

	public List<Company> getCompanies() {
		return mCompanies;
	}

	public Credit getCredits() {
		return mCredits;
	}

	public int getId() {
		return mId;
	}

	public String getOverview() {
		return mOverview;
	}

	public String getPosterPath() {
		return mPosterPath;
	}

	public String getReleaseDate() {
		return mReleaseDate;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getStatus() {
		return mStatus;
	}

	public double getVoteAverage() {
		return mVoteAverage;
	}

	public int getVoteCount() {
		return mVoteCount;
	}
}
