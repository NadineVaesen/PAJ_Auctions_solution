package be.pxl.auctions.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@NamedQueries(
        @NamedQuery(name = "findAllAuction", query = "SELECT a FROM Auction a")
)
public class Auction {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private LocalDate endDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids = new ArrayList<>();


    public Auction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate, DateTimeFormatter dateFormat) {
        this.endDate = endDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public boolean isFinished() {
        return getEndDate().isBefore(LocalDate.now());
    }

    public Bid findHighestBid() {
        double highestBid = 0;
        bids.sort(Comparator.comparing(Bid::getAmount).reversed());
        return bids.get(0);
    }

}
