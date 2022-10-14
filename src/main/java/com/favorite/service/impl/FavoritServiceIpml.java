package com.favorite.service.impl;

import java.util.List;
import java.util.Set;

import com.favorite.model.FavoriteDAO;
import com.favorite.model.FavoriteJNDIDAO;
import com.favorite.model.FavoriteVO;
import com.favorite.service.FavoriteService;
import com.store_img.model.Store_imgVO;

public class FavoritServiceIpml implements FavoriteService {
	private FavoriteDAO dao;

	public FavoritServiceIpml() {
		dao = new FavoriteJNDIDAO();
	}

	@Override
	public boolean insert(FavoriteVO favoriteVO) {
		Integer favorite_id = favoriteVO.getFavorite_id();
		if (favorite_id < 0) {
			return false;
		}
		Integer store_id = favoriteVO.getStore_id();
		if (store_id < 0) {
			return false;
		}
		Integer member_id = favoriteVO.getMember_id();
		if (member_id < 0) {
			return false;
		}
		dao.insert(favoriteVO);
		return true;
	}

	@Override
	public FavoriteVO delete(FavoriteVO vo) {

		Integer favoriteId = vo.getFavorite_id();
		if (favoriteId != null) {
			dao.delete(favoriteId);
			vo.setSuccessful(true);
			return vo;
		} else {
			vo.setSuccessful(false);
			vo.setMessage("請洽詢管理員");
			return vo;
		}

	}

	@Override
	public FavoriteVO findByPrimaryKey(Integer member_id) {

		return dao.findByPrimaryKey(member_id);
	}

	@Override
	public List<FavoriteVO> getAll(Integer member_id) {
//		dao.getAll(member_id).forEach(System.out::println);
		return dao.getAll(member_id);
	}

	@Override
	public FavoriteVO getStoreImgByStoreId(Integer member_id, Integer store_id) {
		return dao.getStoreImgByStoreId(member_id, store_id);
	}

	@Override
	public List<FavoriteVO> getAllM(Integer id) {
		return dao.getAllM(id);
	}

	@Override
	public Store_imgVO getStoreImgByMemberId(Integer member_id) {
		return dao.getStoreImgByMemberId(member_id);
	}

	// MAP
	@Override
	public FavoriteVO addFavorite(Integer store_id, Integer member_id) {
		FavoriteVO result = new FavoriteVO(store_id, member_id);
		dao.insert(result);
		return result;
	}

	@Override
	public FavoriteVO deleteFavorite(Integer store_id, Integer member_id) {
		FavoriteVO result = new FavoriteVO(store_id, member_id);
		dao.deleteF(result);
		return result;
	}

}
