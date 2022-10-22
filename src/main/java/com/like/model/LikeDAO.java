package com.like.model;

import java.util.List;

public interface LikeDAO {

    List<LikeVO> getAll();

    boolean get(Integer forum_id,Integer member_id);

    boolean add(LikeVO obj);

    boolean update(LikeVO oldObj,LikeVO newObj);

    boolean delete(LikeVO obj);
}
