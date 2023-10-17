package com.derezhenko.net.server;

import com.derezhenko.net.dao.Dao;
import com.derezhenko.net.dao.MasterDAO;
import com.derezhenko.net.model.Master;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listMaster(request, response);
    }

    private void listMaster(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MasterDAO dao = new MasterDAO();

        List<Master> listMaster = dao.getAll();
        request.setAttribute("listMaster", listMaster);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int masterId = Integer.parseInt(request.getParameter("name"));

        request.setAttribute("selectedMasterId", masterId);

        listMaster(request, response);
    }
}
