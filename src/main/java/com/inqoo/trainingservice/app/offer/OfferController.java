package com.inqoo.trainingservice.app.offer;

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
    private List<Offer> getAllOffers() {
        return offerService.getAll();
    }

    @PostMapping(value = "/{catId}/{subCatId}/{courseId}/{customerId}")
    private Offer saveNew(@PathVariable("catId") Long catId, @PathVariable("subCatId") Long subCatId, @PathVariable(
            "courseId") Long courseId, @PathVariable("customerId") Long customerId) {
        return offerService.save(customerId, catId, subCatId, courseId);

    }
}
