package com.example.project3.controllers;

import com.example.project3.Models.Genre;
import com.example.project3.Models.Publisher;
import com.example.project3.repo.GenreRepository;
import com.example.project3.repo.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Genre")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    String search_line = "";

    @GetMapping()
    public String Genre(Model model){
        model.addAttribute("Genres", genreRepository.findGenreByNameContains(search_line));
        model.addAttribute("search_line", search_line);
        return "Genre";
    }

    @PostMapping()
    public String GenreSearch(Model model, String search){
        search_line = search;
        return "redirect:/Genre";
    }

    @GetMapping("/Post")
    public String GenreGoPost(@ModelAttribute("genre") Genre new_model,
                                  Model model){
        return "GenreAdd";
    }

    @PostMapping("/Post")
    public String GenrePost(@ModelAttribute("genre") Genre new_model,
                            BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "PublisherAdd";
        }
        genreRepository.save(new_model);
        return "redirect:/Genre";
    }

    @GetMapping("/Update/{id}")
    public String GenreGoUpdate(Model model, @PathVariable("id") long id){
        model.addAttribute("genre", genreRepository.findGenreByIdEquals(id));
        return "GenreUpdate";
    }

    @PostMapping("/Update/{id}")
    public String GenreUpdate(@ModelAttribute("genre") @Valid Genre genre,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") long id){
        if(bindingResult.hasErrors()) {
            return "GenreUpdate";
        }
        genre.setId(id);
        genreRepository.save(genre);
        return "redirect:/Genre";
    }

    @PostMapping("/Delete/{id}")
    public String GenreDelete(Model model, @PathVariable("id") long id){
        genreRepository.deleteById(id);
        return "redirect:/Genre";
    }
}
