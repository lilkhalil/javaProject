package com.example.project.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;


@Controller
public class SelectorController {

    public String getResultSet(@RequestParam String query) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/webapplication", "postgres", "aidar344567");
        if (query.length() < 6 || query.substring(0,6).toLowerCase().equals("select")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
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
        } else {
            String result = "<p>Number of affected rows: ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int affectedRows = preparedStatement.executeUpdate();
            result += affectedRows + "</p>";
            return result;
        } 
    }

    @GetMapping("/selectors")
    public String getPage(Model model) {
        return "selectors";
    }

    @PostMapping("/selectors")
    public String getTable(@RequestParam String query, Model model) {
        try {
            model.addAttribute("table", getResultSet(query));
        } catch (SQLException ex) {
            String sqlException = "";
            for (Throwable e : ex) {
                if (e instanceof SQLException) {
                    sqlException += "<p>SQLState: " + ((SQLException)e).getSQLState() + "</p>";
                    sqlException += "<p>Error Code: " + ((SQLException)e).getErrorCode() + "</p>";
                    sqlException += "<p>Message: " + e.getMessage() + "</p>";
                }
            }
            model.addAttribute("sqlException", sqlException);
        }
        return "selectors";
    } 
}
