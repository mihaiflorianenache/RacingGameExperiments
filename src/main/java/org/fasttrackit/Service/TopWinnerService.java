package org.fasttrackit.Service;

import org.fasttrackit.Domain.TopWinner;
import org.fasttrackit.Persistence.TopWinnerRepository;
import org.fasttrackit.domain.TopWinner;
import org.fasttrackit.persistence.TopWinnerRepository;
import org.fasttrackit.transfer.TopWinnerListResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TopWinnerService {
    private TopWinnerRepository topWinnerRepository=new TopWinnerRepository();

    public void createTopWinner(TopWinner topWinner) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating top winner entry: "+topWinner);
        topWinnerRepository.createTopWinner(topWinner);
    }

    public TopWinnerListResponse getTopWinners() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving top winners");
        List<TopWinner> topWinners = topWinnerRepository.getTopWinners ();
        return new TopWinnerListResponse(topWinners);
    }
}

