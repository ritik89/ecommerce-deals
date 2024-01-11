package com.ritik.ecommerce.service;

import com.ritik.ecommerce.model.GenericItem;
import com.ritik.ecommerce.model.LimitedDeal;
import com.ritik.ecommerce.repository.DealRepository;
import com.ritik.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DealService {

    @Autowired
    DealRepository dealRepo;
    @Autowired
    ItemRepository itemRepo;

    public void create(LimitedDeal deal) throws Exception {
        // check if limited deal time given is already expired
        if(deal.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new Exception("deal expiry time is wrong");
        }
        // check if limited deal max limit items is greater than the current item stock
        GenericItem item = itemRepo.findById(deal.getItemId()).get();
        if(item == null || item.getAvailableQuantity() < deal.getItemSoldLimit()) {
            throw new Exception("deal max item quantity greater than available item quantity");
        }
        // check if deal price exceeds item price
        if(item.getPrice() < deal.getPrice()) {
            throw new Exception("deal price greater than current item price");
        }
        // save the deal
        dealRepo.save(deal);
    }

    public LimitedDeal readDeal(Long id) throws Exception {
        return dealRepo.findById(id).get();
    }

    public void delete(Long id) throws Exception {
        dealRepo.deleteById(id);
    }

    public void update(Long id, LimitedDeal deal) throws Exception {
        LimitedDeal savedDeal = (LimitedDeal) dealRepo.findById(id).get();
        savedDeal.setItemSoldLimit(deal.getItemSoldLimit());
        savedDeal.setExpiryTime(deal.getExpiryTime());
        dealRepo.save(savedDeal);
    }

    public boolean claimDeal(Long id) {
        LimitedDeal savedDeal = (LimitedDeal) dealRepo.findById(id).get();

        // check if Deal is expired
        if(savedDeal.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        // since one user cannot buy more than one item as part of the deal
        if(savedDeal.getItemSoldLimit() == 0) {
            return false;
        }
        savedDeal.setItemSoldLimit(savedDeal.getItemSoldLimit() - 1);
        dealRepo.save(savedDeal);
        return true;
    }

}
