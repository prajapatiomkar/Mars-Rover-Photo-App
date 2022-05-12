package com.example.project1;

public class Word {
    //    String firstname;
//    String lastname;
//
//    public Word(String firstname, String lastname) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
    private String earthData;
    private String mImgSrc;
    private String mCameraName;

    public Word(String earthData, String mImgSrc, String mCameraName) {
        this.earthData = earthData;
        this.mCameraName = mCameraName;
        this.mImgSrc = mImgSrc;
    }

    public String getEarthData() {
        return earthData;
    }

    public String getmImgSrc() {
        return mImgSrc;
    }

    public String getmCameraName() {
        return mCameraName;
    }
}
