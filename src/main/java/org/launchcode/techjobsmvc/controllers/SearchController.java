package org.launchcode.techjobsmvc.controllers;

import jakarta.websocket.server.PathParam;
import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value="results")
    public String displaySearchResults(@RequestParam String searchTerm, @RequestParam String searchType, Model model) {
        ArrayList<Job> jobs;
        if (searchTerm.equals("all")) {
            jobs = JobData.findAll();
            model.addAttribute("searchTerm", searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("searchTerm", searchType);
        }
        model.addAttribute("jobs", jobs);
        return "search";
    }
}

