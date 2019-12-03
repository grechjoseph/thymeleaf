package com.jg.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    /**
     * The method has to return the name of the View to use. It is also responsible for populating the model's attributes.
     * @param name An optional Request Parameter.
     * @param model The model to be populated. (Bean, populated by default).
     * @return The View's name.
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        //Modify model attributes.
        model.addAttribute("name", name);
        //Return the view's name.
        return "greeting";
    }

    /**
     * The method has to return the name of the View to use. It is also responsible for populating the model's attributes.
     * @param name An optional Request Parameter.
     * @param model The model to be populated. (Bean, populated by default).
     * @return The View's name.
     */
    @GetMapping("/greeting2")
    public String greeting2(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        //Modify model attributes.
        model.addAttribute("name", name);
        //Return the view's name.
        return "subdirectory/greeting2";
    }
}
