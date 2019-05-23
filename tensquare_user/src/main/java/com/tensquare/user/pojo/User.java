package com.tensquare.user.pojo;

import java.util.Date;

public class User {

    private String id;
    private String nickname;
    private String mobile;
    private String sex;
    private String password;
    private Date birthday;
    private String avatar;
    private String email;
    private Date regdate;
    private Date updatedate;
    private Date lastdate;
    private long online;
    private String interest;
    private String personality;
    private int fanscount;
    private int followcount;

    public User() {
    }

//    public User(String nickname, String mobile, String sex, String password, Date birthday, String avatar, String email, Date regdate, Date updatedate, Date lastdate, long online, String interest, String personality, int fanscount, int followcount) {
//        this.nickname = nickname;
//        this.mobile = mobile;
//        this.sex = sex;
//        this.password = password;
//        this.birthday = birthday;
//        this.avatar = avatar;
//        this.email = email;
//        this.regdate = regdate;
//        this.updatedate = updatedate;
//        this.lastdate = lastdate;
//        this.online = online;
//        this.interest = interest;
//        this.personality = personality;
//        this.fanscount = fanscount;
//        this.followcount = followcount;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public long getOnline() {
        return online;
    }

    public void setOnline(long online) {
        this.online = online;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public int getFanscount() {
        return fanscount;
    }

    public void setFanscount(int fanscount) {
        this.fanscount = fanscount;
    }

    public int getFollowcount() {
        return followcount;
    }

    public void setFollowcount(int followcount) {
        this.followcount = followcount;
    }
}
