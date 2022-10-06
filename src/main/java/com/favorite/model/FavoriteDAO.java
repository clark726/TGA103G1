package com.favorite.model;

import java.util.List;

public interface FavoriteDAO {

	public boolean insert(FavoriteVO obj);

	public boolean delete(Integer id);

	public FavoriteVO findByPrimaryKey(Integer id);

	public List<FavoriteVO> getAll(Integer member_id);

	public FavoriteVO getStoreImgByStoreId(Integer member_id, Integer store_id);

	//MAP
	public List<FavoriteVO> getAllM(Integer id);
	public void deleteF(FavoriteVO obj);

}
