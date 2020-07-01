package com.arbaelbarca.depedencymodule.model.modeltopheadlines;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Source implements Parcelable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Object id;

	protected Source(Parcel in) {
		name = in.readString();
	}

	public static final Creator<Source> CREATOR = new Creator<Source>() {
		@Override
		public Source createFromParcel(Parcel in) {
			return new Source(in);
		}

		@Override
		public Source[] newArray(int size) {
			return new Source[size];
		}
	};

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(Object id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Source{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
	}
}