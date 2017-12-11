package com.zhongke.weiduo.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.zhongke.weiduo.app.utils.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 2017/8/18.
 * 类描述：
 */

public class PhotoBrowseInfo implements Parcelable {
    private List<String> photoUrls;
    private int currentPhotoPosition = -1;

    private PhotoBrowseInfo(List<String> photoUrls, int currentPhotoPosition) {
        this.photoUrls = new ArrayList<>(photoUrls);
        this.currentPhotoPosition = currentPhotoPosition;
    }

    public static PhotoBrowseInfo create(List<String> photoUrls, int currentPhotoPosition) {
        return new PhotoBrowseInfo(photoUrls, currentPhotoPosition);
    }

    public int getPhotosCount() {
        if (ValidateUtil.isNull(photoUrls)) return 0;
        return photoUrls.size();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.photoUrls);
        dest.writeInt(this.currentPhotoPosition);
    }

    protected PhotoBrowseInfo(Parcel in) {
        this.photoUrls = in.createStringArrayList();
        this.currentPhotoPosition = in.readInt();
    }

    public static final Creator<PhotoBrowseInfo> CREATOR = new Creator<PhotoBrowseInfo>() {
        @Override
        public PhotoBrowseInfo createFromParcel(Parcel source) {
            return new PhotoBrowseInfo(source);
        }

        @Override
        public PhotoBrowseInfo[] newArray(int size) {
            return new PhotoBrowseInfo[size];
        }
    };

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public int getCurrentPhotoPosition() {
        return currentPhotoPosition;
    }

    public void setCurrentPhotoPosition(int currentPhotoPosition) {
        this.currentPhotoPosition = currentPhotoPosition;
    }

    public static Creator<PhotoBrowseInfo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "PhotoBrowseInfo{" +
                "photoUrls=" + photoUrls +
                ", currentPhotoPosition=" + currentPhotoPosition +
                '}';
    }
}
