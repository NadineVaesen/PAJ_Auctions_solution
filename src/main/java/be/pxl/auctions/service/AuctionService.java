package be.pxl.auctions.service;

import be.pxl.auctions.dao.AuctionDao;
import be.pxl.auctions.model.Auction;
import be.pxl.auctions.rest.resource.AuctionCreateResource;
import be.pxl.auctions.rest.resource.AuctionDTO;
import be.pxl.auctions.util.exception.AuctionNotFoundException;
import be.pxl.auctions.util.exception.InvalidDateException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final AuctionDao auctionDao;

    public AuctionService(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    public Optional<Auction> getAuctionById(long auctionId) {
        return auctionDao.findAuctionById(auctionId).map(this::mapToAuctionCreateResource).orElseThrow(() -> new AuctionNotFoundException("Unable to find Auction with id [" + auctionId + "]"));
    }

    private Optional<Auction> mapToAuctionCreateResource(Auction auction) {
        return Optional.ofNullable(auction);
    }

    public AuctionDTO createAuction(AuctionCreateResource auctionCreateResource){
        return mapToAuctionDTO(auctionDao.saveAuction(mapToAuction(auctionCreateResource)));
    }

    public List<AuctionDTO> findAllAuctions() {
        return auctionDao.findAllAuction().stream().map(this::mapToAuctionDTO).collect(Collectors.toList());
    }

    private AuctionDTO mapToAuctionDTO(Auction auction) {
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setId(auctionDTO.getId());
        auctionDTO.setBidList(auction.getBids());
        auctionDTO.setHighestBid(auction.findHighestBid().getAmount());
        auctionDTO.setNumberOfBids(auction.getBids().size());
        auctionDTO.setHighestBidBy(auction.findHighestBid().getUser());
        return auctionDTO;
    }

    private Auction mapToAuction(AuctionCreateResource auctionCreateResource) {
        Auction auction = new Auction();
        auction.setDescription(auctionCreateResource.getDescription());
        try {
            auction.setEndDate(LocalDate.parse(auctionCreateResource.getEndDate()), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("[" + auctionCreateResource.getEndDate() + "] is not a valid date. Excepted format: dd/MM/yyyy");
        }
        return auction;
    }
}
