package com.member_forum_img.model;


import java.util.List;

public interface Member_forum_imgVODAO {
    List<Member_forum_imgVO> getAll();

    Member_forum_imgVO get(Integer manager_id);

    boolean add(Member_forum_imgVO obj);

    boolean update(Member_forum_imgVO obj);

    boolean delete(Integer member_forum_img_id);
}
