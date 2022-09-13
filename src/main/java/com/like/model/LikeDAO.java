package com.like.model;

import java.util.List;

public interface LikeDAO {

    List<LikeVO> getAll();

    LikeVO get(Integer forum_id,Integer member_id);

    boolean add(LikeVO obj);

    boolean update(LikeVO oldObj,LikeVO newObj);

    boolean delete(Integer forum_id,Integer member_id);
}
