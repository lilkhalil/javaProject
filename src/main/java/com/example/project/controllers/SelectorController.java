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
            String result = "<p>" + query.substring(0, 6) + ": Number of affected rows: ";
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

    @GetMapping("/generators")
    public String getGenerator() {
        return "generators";
    }


    public String getTable(String query) {
        try {
            return getResultSet(query);
        } catch (SQLException ex) {
            String sqlException = "";
            for (Throwable e : ex) {
                if (e instanceof SQLException) {
                    sqlException += "<p>SQLState: " + ((SQLException)e).getSQLState() + "</p>";
                    sqlException += "<p>Error Code: " + ((SQLException)e).getErrorCode() + "</p>";
                    sqlException += "<p>Message: " + e.getMessage() + "</p>";
                }
            }
            return sqlException;
        }
    }
    
    @PostMapping("/select")
    public String getSelect(@RequestParam String columnName,
    @RequestParam String tableName,
    @RequestParam(required = false) String whereColumn,
    @RequestParam(required = false) String orderBy,
    Model model
    ) {
        String result = "SELECT " + columnName.trim() + " FROM " + tableName.trim();
        if (!whereColumn.isEmpty()) {
            result += " WHERE " + whereColumn.trim();
        }
        if (!orderBy.isEmpty()) {
            result += " ORDER BY " + orderBy.trim();
        }
        model.addAttribute("result", getTable(result));
        return "generators";
    }

    @PostMapping("/insert")
    public String getInsert(@RequestParam String tableName, @RequestParam String values, Model model) {
        String result = "INSERT INTO " + tableName.trim() + " VALUES " + values.trim();
        model.addAttribute("result", getTable(result));
        return "generators";
    }

    @PostMapping("/update")
    public String getUpdate(@RequestParam String tableName, @RequestParam String value, @RequestParam(required = false) String whereColumn, Model model) {
        String result = "UPDATE " + tableName.trim() + " SET " + value.trim();
        if (!whereColumn.isEmpty()) {
            result += " WHERE " + whereColumn.trim();
        }
        model.addAttribute("result", getTable(result));
        return "generators";
    }

    @PostMapping("/delete")
    public String getDelete(@RequestParam String tableName, @RequestParam(required = false) String whereColumn, Model model) {
        String result = "DELETE FROM " + tableName.trim();
        if (!whereColumn.isEmpty()) {
            result += " WHERE " + whereColumn.trim();
        }
        model.addAttribute("result", getTable(result));
        return "generators";
    }

    @PostMapping("/selectors")
    public String getResult(@RequestParam String query, Model model) {
        model.addAttribute("result", getTable(query));
        return "selectors";
    }

}
