package com.inqoo.trainingservice.app.offer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/offer")
@Slf4j
class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    private List<OfferDTO> getAllOffers() {
        log.info("Getting list of offers");
        return offerService.getAll();
    }

    @PostMapping
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO createdOffer = offerService.create(offerDTO);
        log.info("Saving new offer: " +offerDTO.getCourses()+ " for customer mail: "+offerDTO.getMail());
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

}
