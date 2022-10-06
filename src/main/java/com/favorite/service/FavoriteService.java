package com.favorite.service;

import java.util.List;

import com.favorite.model.FavoriteVO;

public interface FavoriteService {
	
	boolean insert(FavoriteVO favoriteVO);
	
	FavoriteVO delete(FavoriteVO vo);
	
	FavoriteVO findByPrimaryKey(Integer id);
	
	List<FavoriteVO> getAll(Integer member_id);
	
	FavoriteVO getStoreImgByStoreId(Integer member_id, Integer store_id);
	
	//MAP
	public List<FavoriteVO> getAllM(Integer id);
	public FavoriteVO addFavorite(Integer store_id, Integer member_id);
	public FavoriteVO deleteFavorite(Integer store_id,Integer member_id) ;
}
