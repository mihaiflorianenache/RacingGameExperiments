package org.fasttrackit.Transfer;

import org.fasttrackit.Domain.TopWinner;
import org.fasttrackit.domain.TopWinner;

import java.util.List;

public class TopWinnerListResponse {
    private List<TopWinner> content;

    public TopWinnerListResponse(List<TopWinner> content){
        this.content=content;
    }

    public TopWinnerListResponse(){
        //used for deserialization
    }

    public List<TopWinner> getContent() {
        return content;
    }

    public void setContent(List<TopWinner> content) {
        this.content = content;
    }
}

