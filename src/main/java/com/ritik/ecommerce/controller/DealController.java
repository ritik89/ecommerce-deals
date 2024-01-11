package com.ritik.ecommerce.controller;

import com.ritik.ecommerce.model.LimitedDeal;
import com.ritik.ecommerce.model.LimitedDealWithValidTillDays;
import com.ritik.ecommerce.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class DealController {
    @Autowired
    DealService service;

    @PostMapping(value = "/deal")
    public String createDeal(@RequestBody LimitedDealWithValidTillDays deal) throws Exception {
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(deal.daysToExpire);
        LimitedDeal newDeal = new LimitedDeal(deal.description, deal.itemId, deal.itemSoldLimit, expiryDate, deal.price);
        service.create(newDeal);
        return "successfully saved deal";
    }

    @GetMapping(value = "/deal")
    public LimitedDeal getDeal(@RequestParam(name = "id", required = true) Long id) throws Exception {
        return service.readDeal(id);
    }

    @DeleteMapping(value = "/deal")
    public String endDeal(@RequestParam(name = "id", required = true) Long id) throws Exception {
        service.delete(id);
        return "successfully saved deal";
    }

    @PostMapping(value = "/deal/claim")
    public String claimDeal(@RequestBody LimitedDeal deal) {
        boolean status = service.claimDeal(deal.getId());
        if(status) {
            return "deal claimed successfully!";
        }
        return "deal cannot be claimed!";
    }
}
