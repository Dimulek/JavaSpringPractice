package com.example.project3.controllers;

import com.example.project3.Models.Publisher;
import com.example.project3.repo.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Publisher")
@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('LIB')")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    String search_line = "";

    @GetMapping()
    public String Publisher(Model model){
        Iterable<Publisher> publishers = publisherRepository.findPublishersByNameContains(search_line);
        model.addAttribute("Publishers", publishers);
        model.addAttribute("search_line", search_line);
        return "Publisher";
    }

    @PostMapping()
    public String PublisherSearch(Model model, String search){
        search_line = search;
        return "redirect:/Publisher";
    }

    @GetMapping("/Post")
    public String PublisherGoPost(@ModelAttribute("publisher") Publisher new_model,
                                  Model model){
        return "PublisherAdd";
    }

    @PostMapping("/Post")
    public String PublisherPost(@ModelAttribute("publisher") @Valid Publisher new_model,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "PublisherAdd";
        }
        publisherRepository.save(new_model);
        return "redirect:/Publisher";
    }

    @GetMapping("/Update/{id}")
    public String PublisherGoUpdate(Model model, @PathVariable("id") long id){
        model.addAttribute("publisher", publisherRepository.findPublisherByIdEquals(id));
        return "PublisherUpdate";
    }

    @PostMapping("/Update/{id}")
    public String PublisherUpdate(@ModelAttribute("publisher") @Valid Publisher publisher,
                                  BindingResult bindingResult, Model model, @PathVariable("id") long id){
        if(bindingResult.hasErrors()) {
            return "PublisherUpdate";
        }
        publisher.setId(id);
        publisherRepository.save(publisher);
        return "redirect:/Publisher";
    }

    @PostMapping("/Delete/{id}")
    public String PublisherDelete(Model model, @PathVariable("id") long id){
        publisherRepository.deleteById(id);
        return "redirect:/Publisher";
    }
}
