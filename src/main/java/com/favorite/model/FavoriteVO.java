package com.favorite.model;

import java.io.Serializable;

import com.common.Result;

public class FavoriteVO extends Result{

    private Integer favorite_id;
    private Integer store_id;
    private Integer member_id;
    private byte[] img;
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FavoriteVO() {}
    
    public FavoriteVO(Integer store_id,Integer member_id) {
		super();
		this.store_id = store_id;
		this.member_id = member_id;
	}

	public FavoriteVO(Integer favorite_id, Integer store_id,Integer member_id) {
        super();
        this.favorite_id = favorite_id;
        this.store_id = store_id;
		this.member_id = member_id;
    }
    public Integer getFavorite_id() {
        return favorite_id;
    }
    public void setFavorite_id(Integer favorite_id) {
        this.favorite_id = favorite_id;
    }
    public Integer getStore_id() {
        return store_id;
    }
    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }
    public Integer getMember_id() {
        return member_id;
    }
    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }
    @Override
    public String toString() {
        return "FavoriteVO [favorite_id=" + favorite_id + ", store_id=" + store_id + ", member_id=" + member_id + "]";
    }

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] bs) {
		this.img = bs;
	}
}
