package org.fasttrackit.Persistence;

import org.fasttrackit.Domain.TopWinner;
import org.fasttrackit.domain.TopWinner;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopWinnerRepository {
    public void createTopWinner(TopWinner topWinner) throws SQLException, IOException, ClassNotFoundException {

        //connection to database is achieving
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            //character '?' is using for combating sql injection and so the parameters are putted with PreparedStatement
            String insertSql = "INSERT INTO topwinners (`name`,`wonRaces`) VALUES (?,?);" + "ON DUPLICATE KEY UPDATE wonRaces=wonRaces+1;";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, topWinner.getName());
            preparedStatement.setInt(2, topWinner.getWonRaces());

            preparedStatement.executeUpdate();
        }
    }

    public List<TopWinner> getTopWinners() throws SQLException, IOException, ClassNotFoundException {
        try(Connection connection=DatabaseConfiguration.getConnection()){
            String query = "SELECT id, `name`, wonRaces FROM topwinners ORDER BY wonRaces DESC";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            List<TopWinner> response=new ArrayList<>();
            while(resultSet.next()){
                TopWinner topWinner=new TopWinner();
                topWinner.setId(resultSet.getLong("id"));
                topWinner.setName(resultSet.getString("name"));
                topWinner.setWonRaces(resultSet.getInt("wonRaces"));

                response.add(topWinner);
            }
            return response;
        }
    }
}


