package com.like.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.like.model.LikeVO;
import com.like.model.service.AddLikeService;
import com.like.model.service.impl.AddLikeServiceImpl;

@WebServlet("/DeleteLike")
public class DeleteLike extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();
    private AddLikeService addLike = new AddLikeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        LikeVO likeVO = gson.fromJson(request.getReader().readLine(), LikeVO.class);
        LikeVO result =  addLike.delete(likeVO);
        
        response.setContentType("application/json");
        try (PrintWriter pw = response.getWriter()) {
            pw.print(new GsonBuilder().create().toJson(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
