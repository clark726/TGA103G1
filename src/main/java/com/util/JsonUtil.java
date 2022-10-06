package com.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manager.model.ManagerVO;

public class JsonUtil {
	private static Gson gson = new Gson();
	private JsonUtil() {}
	public static String managerToString(ManagerVO vo) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", vo.getManager_id());
		map.put("account", vo.getAccount());
		map.put("password", vo.getPassword());
		map.put("status", vo.getStatus());
		map.put("birthday", vo.getBirthday().toString());
		LocalDateTime loginTime = vo.getLastLoginTime();
		if(loginTime != null) {
			map.put("lastLoginTime", loginTime.toString());
		}else {
			map.put("lastLoginTime", Apadter.getLocalDateTimeNow().toString());
		}
		return gson.toJson(map);
	}
	
	public static ManagerVO stringToManager(String str) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] data = decoder.decode(str.getBytes());
		JsonObject jb = gson.fromJson(new String(data), JsonObject.class);
		ManagerVO vo = new ManagerVO();
		vo.setAccount(jb.get("account").getAsString());
		vo.setPassword(jb.get("password").getAsString());
		vo.setStatus(jb.get("status").getAsInt());
		vo.setBirthday(Apadter.stringToLocalDate(jb.get("birthday").getAsString()));
		vo.setLastLoginTime(Apadter.stringToLocalDateTime(jb.get("lastLoginTime").getAsString()));
		return vo;
	}
	public static void main(String[] args) {
		ManagerVO vo = new ManagerVO();
		vo.setManager_id(3);
		vo.setAccount("abc123");
		vo.setPassword("password");
		vo.setStatus(2);
		vo.setBirthday(LocalDate.now());
//		vo.setLastLoginTime(Apadter.getLocalDateTimeNow());
//		String manStr = JsonUtil.managerToString(vo);
		Base64.Encoder encoder = Base64.getEncoder();
//		String b64Encode = encoder.encodeToString(manStr.getBytes());
//		ManagerVO vo2 = stringToManager(b64Encode);
//		System.out.println(manStr);
//		JsonObject jb = gson.fromJson(manStr, JsonObject.class);
//		System.out.println(jb.get("lastLoginTime"));
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String time = ldt.format(df);
		System.out.println(LocalDateTime.parse(time,df));
	}
}
