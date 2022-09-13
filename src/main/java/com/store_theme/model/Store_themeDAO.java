package com.store_theme.model;

import java.util.List;

public interface Store_themeDAO {

	public void insert(Store_themeVO store_theme);
	public void update(Store_themeVO store_theme);
	public void delete(Integer theme_id);
	public Store_themeVO findByPrimaryKey(Integer theme_id);
	public List<Store_themeVO>getAll();
}
