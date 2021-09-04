package com.inqoo.trainingservice.app.offer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/offer")
class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    private List<OfferDTO> getAllOffers() {
        return offerService.getAll();
    }

    @PostMapping
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO createdOffer = offerService.create(offerDTO);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

}
