package com.jg.thymeleaf.controller;

import com.jg.thymeleaf.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ThymeleafController {

    Map<Integer, Client> clientMap = new HashMap<>() {
        {
            put(1, new Client("Joseph", "Grech", 29));
            put(2, new Client("Nathalie", "Grima", 31));
            put(3, new Client("Joseph", "Grima", 30));
        }
    };

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
            String surname = e.getValue().getSurname();
            links += "<a href='clients/" + id + "'>" + name + " " + surname + "</a><br/>";
        }

        // Add attribute of links' HTML to generate
        model.addAttribute("links", links);
        // Add attribute of new instance of Client for POSTing
        model.addAttribute("client", new Client());
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

    @PostMapping("/clients")
    public String addClient(@ModelAttribute(value="client") Client client, Model model) {
        clientMap.put(clientMap.size() + 1, client);
        return getClients(model);
    }
}
