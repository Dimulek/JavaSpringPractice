package com.example.project3.controllers;

import com.example.project3.Models.Book;
import com.example.project3.repo.AuthorRepository;
import com.example.project3.repo.BookRepository;
import com.example.project3.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/Book")
@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('CATALOG')")
public class BookController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;

    String search_line = "";

    @GetMapping()
    public String Book(Model model){
        Iterable<Book> books = bookRepository.findBooksByNameContains(search_line);
        model.addAttribute("Books", books);
        model.addAttribute("search_line", search_line);
        return "Book";
    }

    @PostMapping()
    public String BookSearch(Model model, String search){
        search_line = search;
        return "redirect:/Book";
    }

    @GetMapping("/Post")
    public String BookGoPost(@ModelAttribute("book") Book new_model,
                              Model model){
        model.addAttribute("Author", authorRepository.findAll());
        return "BookAdd";
    }

    @PostMapping("/Post")
    public String BookPost(@ModelAttribute("book") @Valid Book new_model,
                           BindingResult bindingResult,
                           @RequestParam("author_id") long author_id, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("Author", authorRepository.findAll());
            return "BookAdd";
        }
        new_model.setAuthor(authorRepository.findAuthorByIdEquals(author_id));
        bookRepository.save(new_model);
        return "redirect:/Book";
    }

    @GetMapping("/Update/{id}")
    public String BookGoUpdate(Model model, @PathVariable("id") long id){
        var buff = bookRepository.findBookByIdEquals(id);
        model.addAttribute("book", buff);
        model.addAttribute("Authors", authorRepository.findAll());
        model.addAttribute("Genres", genreRepository.findAll());
        return "BookUpdate";
    }

    @PostMapping("/Update/{id}")
    public String BookUpdate(@ModelAttribute("book") @Valid Book book,
                             @RequestParam long author,
                             BindingResult bindingResult, Model model,
                             @PathVariable("id") long id){
        if(bindingResult.hasErrors()) {
            var buff = bookRepository.findBookByIdEquals(id);
            model.addAttribute("book", buff);
            model.addAttribute("Authors", authorRepository.findAll());
            model.addAttribute("Genres", genreRepository.findAll());
            return "BookUpdate";
        }
        book.setId(id);
        book.setAuthor(authorRepository.findAuthorByIdEquals(author));
        bookRepository.save(book);
        return "redirect:/Book";
    }

    @PostMapping("/Update/{id}/AddGenre")
    public String BookAddGenre(@PathVariable("id") long id_book,
                               @RequestParam("genre_id") long id_genre){
        var book = bookRepository.findBookByIdEquals(id_book);
        book.addGenres(genreRepository.findGenreByIdEquals(id_genre));
        bookRepository.save(book);
        return "redirect:/Book/Update/" + id_book;
    }

    @PostMapping("/Update/{id_book}/DeleteGenre/{id_genre}")
    public String BookDeleteGenre(@PathVariable("id_book") long id_book,
                                  @PathVariable("id_genre") long id_genre){
        var book = bookRepository.findBookByIdEquals(id_book);
        book.deleteGenres(id_genre);
        bookRepository.save(book);
        return "redirect:/Book/Update/" + id_book;
    }

    @PostMapping("/Delete/{id}")
    public String BookDelete(Model model, @PathVariable("id") Long id){
        bookRepository.deleteById(id);
        return "redirect:/Book";
    }
}
