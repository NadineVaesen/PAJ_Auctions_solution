package be.pxl.auctions.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    public void setEndDate(LocalDate endDate) {
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
        if (bids.isEmpty()) {
            return new Bid();
        } else {
            bids.sort(Comparator.comparing(Bid::getAmount).reversed());
            return bids.get(0);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auction)) return false;
        Auction auction = (Auction) o;
        return getId() == auction.getId() && Objects.equals(getDescription(), auction.getDescription()) && Objects.equals(getEndDate(), auction.getEndDate()) && Objects.equals(getBids(), auction.getBids());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getEndDate(), getBids());
    }
}
