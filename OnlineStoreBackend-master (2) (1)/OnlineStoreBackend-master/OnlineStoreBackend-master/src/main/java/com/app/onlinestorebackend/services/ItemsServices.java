package com.app.onlinestorebackend.services;


import com.app.onlinestorebackend.DTOs.GeneralResponse;
import com.app.onlinestorebackend.DTOs.GeneralResponseBody;
import com.app.onlinestorebackend.entities.Item;
import com.app.onlinestorebackend.repos.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ItemsServices {

    private final ItemRepo itemRepo;

    public GeneralResponse addItemToInventory(Item item){
        if(itemRepo.existsByIdIs(item.getId())){
            return new GeneralResponse(
                    HttpStatus.BAD_REQUEST,
                    "Item with this Id already exists!",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody<>(null)
            );
        }else{
            return new GeneralResponse(
                    HttpStatus.ACCEPTED,
                    "Item Added to Inventory",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody<>(itemRepo.save(item))
            );
        }
    }


    public GeneralResponse viewItemDetails(String itemId){
        if(itemRepo.existsByIdIs(itemId)){
            return new GeneralResponse(
                    HttpStatus.ACCEPTED,
                    "Item Details Sent",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody<>(itemRepo.findItemByIdIs(itemId))
            );

        }else{
            return new GeneralResponse(
                    HttpStatus.BAD_REQUEST,
                    "Item with this Id doesn't exists!",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody<>(null)
            );
        }
    }

    public GeneralResponse viewAllItems(){

            return new GeneralResponse(
                    HttpStatus.ACCEPTED,
                    "Item Details Sent",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody<>(itemRepo.findAll())
            );
    }
}
