package com.favorite.model;

import java.util.List;

public interface FavoriteDAO {
	public void insert(FavoriteVO obj);

	public void update(FavoriteVO obj);

	public void delete(Integer id);

	public FavoriteVO findByPrimaryKey(Integer id);

	public List<FavoriteVO> getAll();
}
