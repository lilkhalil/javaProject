package com.example.project.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;


@Controller
public class SelectorController {

    public ResultSet getTable(String query) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/webapplication", "postgres", "aidar344567");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return null;
        }
    }

    public String tableToHTML(ResultSet rs) throws Exception {
        String result = "<table id=\"table\" class=\"table table-striped table-hover\"><thead><tr>";
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            result += "<th>" + rsmd.getColumnLabel(i + 1) + "</th>";
        }
        result += "</tr></thead><tbody>";
        while (rs.next()) {
            result += "<tr>";
            for (int i = 0; i < columnCount; i++) {
                result += "<td>" + rs.getString(i + 1) + "</td>";
            }
            result += "</tr>";
        }
        result += "</tbody></table>";
        return result;
    }

    @GetMapping("/selectors")
    public String getPage(Model model) {
        return "selectors";
    }

    @PostMapping("/selectors")
    public String getTable(@RequestParam String query, Model model) {
        try {
            model.addAttribute("table", tableToHTML(getTable(query)));
        } catch (SQLException e) {
            model.addAttribute("sqlError", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "selectors";
    } 
}
