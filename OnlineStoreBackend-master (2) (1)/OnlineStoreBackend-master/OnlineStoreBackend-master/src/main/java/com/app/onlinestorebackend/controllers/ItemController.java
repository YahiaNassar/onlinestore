package com.app.onlinestorebackend.controllers;


import com.app.onlinestorebackend.DTOs.GeneralResponse;
import com.app.onlinestorebackend.DTOs.GeneralResponseBody;
import com.app.onlinestorebackend.entities.Item;
import com.app.onlinestorebackend.services.ItemsServices;
import com.app.onlinestorebackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemsServices itemsServices;
    @Autowired
    private UserServices userServices;


    @PostMapping("/addnew/{username}/{password}")
    @ResponseBody
    private GeneralResponse addNewItemToInventory(@RequestBody Item item,
                                                  @PathVariable String username,
                                                  @PathVariable String password){

        if(!userServices.usernameAndPasswordChecker(username, password)){
            return new GeneralResponse(
                    HttpStatus.UNAUTHORIZED,
                    "Wrong Security Credentials",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody(null)
            );
        }

        return itemsServices.addItemToInventory(item);
    }


    @GetMapping("/itemdetails/{itemId}/{username}/{password}")
    @ResponseBody
    private GeneralResponse viewItemDetails(@PathVariable String itemId,
                                                  @PathVariable String username,
                                                  @PathVariable String password){

        if(!userServices.usernameAndPasswordChecker(username, password)){
            return new GeneralResponse(
                    HttpStatus.UNAUTHORIZED,
                    "Wrong Security Credentials",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody(null)
            );
        }

        return itemsServices.viewItemDetails(itemId);
    }

    @GetMapping("/AllItems/{username}/{password}")
    @ResponseBody
    private GeneralResponse viewAllItems(
                                            @PathVariable String username,
                                            @PathVariable String password){

        if(!userServices.usernameAndPasswordChecker(username, password)){
            return new GeneralResponse(
                    HttpStatus.UNAUTHORIZED,
                    "Wrong Security Credentials",
                    LocalDate.now(),
                    LocalTime.now(),
                    new GeneralResponseBody(null)
            );
        }

        return itemsServices.viewAllItems();
    }



}
