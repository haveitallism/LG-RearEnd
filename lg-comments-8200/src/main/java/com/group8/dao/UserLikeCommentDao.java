package com.group8.dao;

import com.group8.entity.LgComment;
import com.group8.entity.LgUserLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserLikeCommentDao {
    void save(LgUserLike lgUserLike);

    List<LgUserLike> saveAll(List<LgUserLike> list);

    Page<LgUserLike> findByLikedUserIdAndStatus(String likedUserId, Integer code, Pageable pageable);

    Page<LgUserLike> findByLikedPostIdAndStatus(String likedPostId, Integer code, Pageable pageable);

    LgUserLike findByLikedUserIdAndLikedPostId(@Param("uid") String likedUserId, @Param("pid") String likedPostId);

    LgComment findById(String cid);

    void numUpdate(LgComment lgComment);

    void likeUpdate(LgUserLike ul);
}
