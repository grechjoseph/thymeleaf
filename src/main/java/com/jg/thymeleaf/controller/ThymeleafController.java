package com.jg.thymeleaf.controller;

import com.jg.thymeleaf.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ThymeleafController {

    Map<Integer, Client> clientMap = Map.of(
            1, Client.builder().name("Joseph").surname("Grech").age(29).build(),
            2, Client.builder().name("Nathalie").surname("Grima").age(31).build(),
            3, Client.builder().name("Joseph").surname("Grima").age(30).build()
    );

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

    @GetMapping("/clients")
    public String getClients(Model model) {
        String links = "";

        for(Map.Entry<Integer, Client> e : clientMap.entrySet()){
            int id = e.getKey();
            String name = e.getValue().getName();
            links += "<a href='clients/" + id + "'>" + name + "</a><br/>";
        }

        model.addAttribute("links", links);
        return "subdirectory/clients";
    }

    @GetMapping("/clients/{clientId}")
    public String getClient(@PathVariable Integer clientId, Model model){
        Client client = clientMap.get(clientId);
        model.addAttribute("name", client.getName());
        model.addAttribute("surname", client.getSurname());
        model.addAttribute("age", client.getAge());
        return "/subdirectory/client";
    }
}
