package com.example.MyBookShopApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping(path={"/bookShop" })
public class GenresPageController {

    @GetMapping("/genres")
    public String genresPage() {
        Logger.getLogger(GenresPageController.class.getName()).info("printing genres page");
        return "genres/index";
    }
}
