package com.example.project3.controllers;

import com.example.project3.Models.Author;
import com.example.project3.Models.Copy;
import com.example.project3.repo.AuthorRepository;
import com.example.project3.repo.BookRepository;
import com.example.project3.repo.CopyRepository;
import com.example.project3.repo.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Copy")
public class CopyController {
    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;


    @GetMapping()
    public String Copy(Model model){
        Iterable<Copy> authors = copyRepository.findAll();
        model.addAttribute("Copies", authors);
        return "Copy";
    }

    @GetMapping("/Post")
    public String CopyGoPost(@ModelAttribute("copy") Copy copy, Model model){
        model.addAttribute("Books", bookRepository.findAll());
        model.addAttribute("Publishers", publisherRepository.findAll());
        return "CopyAdd";
    }

    @PostMapping("/Post")
    public String CopyPost(@ModelAttribute("copy") @Valid Copy copy,
                            BindingResult bindingResult,
                           @RequestParam("book_id") long book_id,
                           @RequestParam("publisher_id") long publisher_id, Model model){
        if(bindingResult.hasErrors()) {
            return "CopyAdd";
        }
        copy.setBook(bookRepository.findBookByIdEquals(book_id));
        copy.setPublisher(publisherRepository.findPublisherByIdEquals(publisher_id));
        copyRepository.save(copy);
        return "redirect:/Copy";
    }

    @GetMapping("/Update/{id}")
    public String CopyGoUpdate(Model model, @PathVariable("id") long id){
        model.addAttribute("copy", copyRepository.findCopyByIdEquals(id));
        model.addAttribute("Books", bookRepository.findAll());
        model.addAttribute("Publishers", publisherRepository.findAll());
        return "CopyUpdate";
    }

    @PostMapping("/Update/{id}")
    public String CopyUpdate(@ModelAttribute("copy") @Valid Copy copy,
                             BindingResult bindingResult,
                             @RequestParam("book_id") long book_id,
                             @RequestParam("publisher_id") long publisher_id,
                             Model model, @PathVariable("id") long id){
        if(bindingResult.hasErrors()) {
            return "CopyUpdate";
        }
        copy.setId(id);
        copy.setBook(bookRepository.findBookByIdEquals(book_id));
        copy.setPublisher(publisherRepository.findPublisherByIdEquals(publisher_id));
        copyRepository.save(copy);
        return "redirect:/Copy";
    }

    @PostMapping("/Delete/{id}")
    public String CopyDelete(Model model, @PathVariable("id") Long id){
        copyRepository.deleteById(id);
        return "redirect:/Copy";
    }
}
