package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    public void addlike(String userid, String friendid) {
        //先查询是否添加对方为好友
        int num = friendDao.selectcount(userid, friendid);
        if(num>0){
            return;
        }
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //判断对方是否喜欢你，喜欢则更改为互相喜欢
        if(friendDao.selectcount(friendid, userid)> 0){
//            friend.setIslike("1");
//            friendDao.save(friend);
//            friend.setFriendid(userid);
//            friend.setUserid(friendid);
//            friendDao.save(friend);
            friendDao.flush();
            friendDao.updatelike("1", userid, friendid);
            friendDao.flush();
            friendDao.updatelike("1", friendid, userid);
        }
    }
}
