package com.like.model.service.impl;

import java.util.List;

import com.like.model.LikeVO;
import com.like.model.LikeVOJNDI;
import com.like.model.service.AddLikeService;

public class AddLikeServiceImpl implements AddLikeService {

    private LikeVOJNDI jndi;

    public AddLikeServiceImpl() {
        this.jndi = new LikeVOJNDI();
    }

    @Override
    public List<LikeVO> getAll() {
        return this.jndi.getAll();
    }

    @Override
    public boolean get(Integer forum_id, Integer member_id) {
        	 return this.jndi.get(forum_id, member_id);
    }

    @Override
    public LikeVO add(LikeVO obj) {
       if(jndi.add(obj)) {
           jndi.addFourmLike(obj);
           obj.setSuccessful(true);
           return obj;
       }
       obj.setSuccessful(false);
      return null;
    }

    @Override
    public boolean update(LikeVO obj1, LikeVO obj2) {
        return this.jndi.update(obj1, obj2);
    }

    @Override
    public LikeVO delete(LikeVO obj) {
       if(jndi.delete(obj)) {
           jndi.deleteFourmLike(obj);
           obj.setSuccessful(true);
           return obj;
       }
       obj.setSuccessful(false);
       return obj;
    }

}
