<%@page import="com.store.service.impl.StoreServiceImpl"%>
<%@page import="com.store.service.StoreService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%
StoreService storeSvc = new StoreServiceImpl();
List<StoreVO> list = storeSvc.getAllStore();
pageContext.setAttribute("list", list);
%>
eqfeed_callback( { "type": "FeatureCollection", "features": [
<c:forEach var="storeVO" items="${list}" varStatus="s">{
    <c:if test="${!s.last}">
        "type": "Feature",
       "properties": {"name":"${storeVO.name}","storeId":"${storeVO.store_id}","theme":"${storeVO.theme_id}","produce":"${storeVO.produce}","address":"${storeVO.address}"},
        "geometry": {
          "type": "Point",
          "coordinates": [
            ${storeVO.lat},
            ${storeVO.lng}
          ]
        }
      },
      </c:if>
	<c:if test="${s.last}">
        "type": "Feature",
		"properties": {"name":"${storeVO.name}","storeId":"${storeVO.store_id}","theme":"${storeVO.theme_id}","produce":"${storeVO.produce}","address":"${storeVO.address}"},
        "geometry": {
          "type": "Point",
          "coordinates": [
            ${storeVO.lat},
            ${storeVO.lng}
          ]
        }
      }
      </c:if>
</c:forEach>

] } );
