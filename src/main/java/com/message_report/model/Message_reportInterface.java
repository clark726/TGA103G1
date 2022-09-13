package com.message_report.model;

import java.util.List;

public interface Message_reportInterface<T> {
    public boolean insert(Message_reportVO obj);
    public boolean update(Message_reportVO obj);
    public Message_reportVO findByPrimaryKey(Integer id);
    public List<Message_reportVO> getAll();
	boolean delete(Integer id, Integer member_id, Integer message_id);
}
