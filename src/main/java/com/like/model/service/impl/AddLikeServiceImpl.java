package com.like.model.service.impl;

import java.util.List;

import com.like.model.LikeVO;
import com.like.model.LikeVOJNDI;
import com.like.model.service.AddLikeService;

public class AddLikeServiceImpl implements AddLikeService{

    private LikeVOJNDI jndi;
    public AddLikeServiceImpl() {
        this.jndi = new LikeVOJNDI();
    }
    @Override
    public List<LikeVO> getAll() {
        return this.jndi.getAll();
    }

    @Override
    public LikeVO get(Integer forum_id, Integer member_id) {
        return this.jndi.get(forum_id, member_id);
    }
    @Override
    public boolean add(LikeVO obj) {
        return this.jndi.add(obj);
    }
    @Override
    public boolean update(LikeVO obj1,LikeVO obj2) {
        return this.jndi.update(obj1, obj2);
    }

    @Override
    public boolean delete(Integer forumid,Integer memberid) {
        return this.jndi.delete(forumid, memberid);
    }
    
}
