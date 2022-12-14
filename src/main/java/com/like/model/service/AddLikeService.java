package com.like.model.service;

import java.util.List;

import com.like.model.LikeVO;

public interface AddLikeService {
    public List<LikeVO> getAll();
    public boolean get(Integer forum_id,Integer member_id);
    public LikeVO add(LikeVO obj);
    public boolean update(LikeVO obj1,LikeVO obj2);
    public LikeVO delete(LikeVO obj);
}
