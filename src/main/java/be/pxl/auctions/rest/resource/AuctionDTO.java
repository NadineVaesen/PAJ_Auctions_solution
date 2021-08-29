package be.pxl.auctions.rest.resource;

import be.pxl.auctions.model.Bid;
import be.pxl.auctions.model.User;

import java.time.LocalDate;
import java.util.List;

public class AuctionDTO {

    private long id;
    private List<Bid> bidList;
    private int numberOfBids;
    private double highestBid;
    private User highestBidBy;

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public User getHighestBidBy() {
        return highestBidBy;
    }

    public void setHighestBidBy(User highestBidBy) {
        this.highestBidBy = highestBidBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }
}
