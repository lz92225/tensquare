package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {

    public Friend findByUseridAndFriendid(String userid, String friendid);

    @Query(value = "select count(*) from tb_friend where userid=? and friendid=?",nativeQuery = true)
    int selectcount(String userid, String friendid);

    @Query(value = "update tb_friend set islike=?1 where userid=?2 and friendid=?3", nativeQuery = true)
    void updatelike(String s, String userid, String friend);
}
