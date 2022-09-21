package com.forum.model;

import java.sql.Date;
import java.util.List;

import com.store.model.StoreVO;

public class ForumService {

	private ForumDAO forumdao;
	
	public ForumService() {
		forumdao = new ForumJDBC();
	}
	
	public ForumVO addForum(Integer member_id, String content, String title, Integer like,
			Integer look, Integer message, Integer status) {
		
		ForumVO vo = new ForumVO();
		vo.setMember_id(member_id);
		vo.setContent(content);
		vo.setTitle(title);
		vo.setLike(like);
		vo.setLook(look);
		vo.setMessage(message);
		vo.setStatus(status);
		forumdao.add(vo);
		
		return vo; 
	}
	
	public void insertimg() {
		
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addForum(ForumVO forumVO) {
		forumdao.add(forumVO);
	}
	
//	public ForumVO updatStore(Integer product_id ,String name, Integer price, Integer store_id, String description,
//			Integer type_id, Integer stock , Integer status) {
		
//		ProductVO vo = new ProductVO();
//		vo.setProduct_id(product_id);
//		vo.setName(name);
//		vo.setPrice(price);
//		vo.setStore_id(store_id);
//		vo.setDescription(description);
//		vo.setType_id(type_id);
//		vo.setStock(stock);
//		vo.setStatus(status);
//		productdao.update(vo);
//		
//		return null; 
//	}
	
	public void delete(Integer product_id) {
		forumdao.delete(product_id);
	}
	
	public List<ForumVO> getAll(){
		return forumdao.getAll();
	}
	
//	public ForumVO getOneProduct(Integer product_id) {
//		return forumdao.findByPrimaryKey(product_id);
//	}

	
}
