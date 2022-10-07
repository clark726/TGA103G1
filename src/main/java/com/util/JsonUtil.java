package com.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.manager.model.ManagerVO;

public class JsonUtil {
	private static Gson gson = new Gson();

	private JsonUtil() {
	}

	public static String managerToString(ManagerVO vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", vo.getManager_id());
		map.put("account", vo.getAccount());
		map.put("password", vo.getPassword());
		map.put("status", vo.getStatus());
		map.put("birthday", Apadter.localDateToDate(vo.getBirthday()).getTime());
		map.put("lastLoginTime", Apadter.localDateTimeToDate(vo.getLastLoginTime()).getTime());
		return gson.toJson(map);
	}

	public static ManagerVO base64ToManager(String str) {
//		Base64.Decoder decoder = Base64.getDecoder();
//		byte[] data = decoder.decode(str.getBytes());
		
		JsonObject jb = gson.fromJson(new String(Base64.getDecoder().decode(str.getBytes())), JsonObject.class);
		ManagerVO vo = new ManagerVO();
		vo.setManager_id(jb.get("id").getAsInt());
		vo.setAccount(jb.get("account").getAsString());
		vo.setPassword(jb.get("password").getAsString());
		vo.setStatus(jb.get("status").getAsInt());
		vo.setBirthday(Apadter.dateToLocalDate(new java.util.Date(Long.parseLong(jb.get("birthday").getAsString()))));
		vo.setLastLoginTime(Apadter.dateToLocalDateTime(new java.util.Date(Long.parseLong(jb.get("lastLoginTime").getAsString()))));
		return vo;
	}

	public static void main(String[] args) {
		ManagerVO vo = new ManagerVO();
		vo.setManager_id(3);
		vo.setAccount("abc123");
		vo.setPassword("password");
		vo.setStatus(2);
		vo.setBirthday(LocalDate.now());
		vo.setLastLoginTime(LocalDateTime.now());
//		String manastr = managerToString(vo);
//		System.out.println(manastr);
//		String base64 = Base64.getEncoder().encodeToString(manastr.getBytes());
//		System.out.println(base64);
//		String man = new String(Base64.getDecoder().decode(base64.getBytes()));
//		System.out.println(man);
//		JsonObject jsonObject = gson.fromJson(man, JsonObject.class);
//		System.out.println(Apadter.dateToLocalDateTime(new java.util.Date(Long.
//				parseLong(jsonObject.get("lastLoginTime").getAsString()))));
		String manstr = managerToString(vo);
		System.out.println(manstr);
		ManagerVO man = base64ToManager(Base64.getEncoder().encodeToString(manstr.getBytes()));
		System.out.println(man.getBirthday());
	}
}
