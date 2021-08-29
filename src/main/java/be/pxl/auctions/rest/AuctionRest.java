package be.pxl.auctions.rest;

import be.pxl.auctions.model.Auction;
import be.pxl.auctions.rest.resource.AuctionCreateResource;
import be.pxl.auctions.rest.resource.AuctionDTO;
import be.pxl.auctions.rest.resource.UserDTO;
import be.pxl.auctions.service.AuctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auctions")
public class AuctionRest {
    private static final Logger LOGGER = LogManager.getLogger(AuctionRest.class);

    private final AuctionService auctionService;

    public AuctionRest(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public List<AuctionDTO> findAllAuctions() {
        return auctionService.findAllAuctions();
    }

    @GetMapping("{auctionId}")
    public Optional<Auction> getAuctionByAuctionId(@PathVariable("auctionId") long auctionId){
        return auctionService.getAuctionById(auctionId);
    }

    @PostMapping
    public AuctionDTO createAuction(@RequestBody AuctionCreateResource auctionCreateResource){
        return auctionService.createAuction(auctionCreateResource);
    }

}
