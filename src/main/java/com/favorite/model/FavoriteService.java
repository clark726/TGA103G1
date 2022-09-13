package com.favorite.model;

import java.util.List;

public class FavoriteService {
	private FavoriteJNDIDAO dao;

	public FavoriteService() {
		dao = new FavoriteJNDIDAO();
	}

	public List<FavoriteVO> getAll() {
		return dao.getAll();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public FavoriteVO get(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public FavoriteVO insert(Integer store_id, Integer member_id) {
		FavoriteVO result = new FavoriteVO(store_id, member_id);
		dao.insert(result);
		return result;
	}

	public FavoriteVO update(Integer favorite_id, Integer store_id, Integer member_id) {
		FavoriteVO result = new FavoriteVO(favorite_id, store_id, member_id);
		dao.update(result);
		return result;
	}
}