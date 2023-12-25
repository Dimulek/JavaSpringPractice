package com.example.project3.controllers;

import com.example.project3.Models.Author;
import com.example.project3.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    String search_line = "";

    @GetMapping()
    public String Author(Model model){
        Iterable<Author> authors = authorRepository.findAuthorsByAliasContains(search_line);
        model.addAttribute("Authors", authors);
        model.addAttribute("search_line", search_line);
        return "Author";
    }

    @PostMapping()
    public String AuthorSearch(@RequestParam("search") String search){
        search_line = search;
        return "redirect:/Author";
    }

    @GetMapping("/Post")
    public String AuthorGoAdd(@ModelAttribute("author") Author author, Model model){
        return "AuthorAdd";
    }

    @PostMapping("/Post")
    public String AuthorAdd(@ModelAttribute("author") @Valid Author author,
                            BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "AuthorAdd";
        }
        authorRepository.save(author);
        return "redirect:/Author";
    }

    @GetMapping("/Update/{id}")
    public String AuthorGoUpdate(Model model, @PathVariable("id") long id){
        model.addAttribute("author", authorRepository.findAuthorByIdEquals(id));
        return "AuthorUpdate";
    }

    @PostMapping("/Update/{id}")
    public String AuthorUpdate(@ModelAttribute("author") @Valid Author author,
                               BindingResult bindingResult, Model model, @PathVariable("id") long id){
        if(bindingResult.hasErrors()) {
            return "AuthorUpdate";
        }
        author.setId(id);
        authorRepository.save(author);
        return "redirect:/Author";
    }

    @PostMapping("/Delete/{id}")
    public String AuthorDelete(Model model, @PathVariable("id") Long id){
        authorRepository.deleteById(id);
        return "redirect:/Author";
    }
}
