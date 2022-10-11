package com.like.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.like.model.LikeVO;

@WebServlet("/AddLike")
public class AddLike extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        LikeVO likeVO = gson.fromJson(request.getReader().readLine(), LikeVO.class);
        
        
    }

}
