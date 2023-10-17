package com.derezhenko.net.server;

import com.derezhenko.net.dao.MasterDAO;
import com.derezhenko.net.model.Master;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/masters")
public class GetMastersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MasterDAO masterDAO = new MasterDAO();
        List<Master> masters  =  masterDAO.getAll();

        List<JSONObject> listJson = new ArrayList<>();
        for (Master master:
             masters) {
            JSONObject masterJson = new JSONObject();
            masterJson.put(String.valueOf(master.getId()), master.getName());
            listJson.add(masterJson);
        }

        System.out.println(listJson.toString());
        resp.getWriter().write(listJson.toString());
        super.doGet(req, resp);
    }
}
