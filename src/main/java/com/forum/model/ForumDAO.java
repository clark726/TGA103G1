package com.forum.model;

import java.util.List;

public interface ForumDAO {
    List<ForumVO> getAll();

    ForumVO get(Integer forum_id);

    boolean add(ForumVO obj);

    boolean update(ForumVO obj);

    boolean delete(Integer id);
}
