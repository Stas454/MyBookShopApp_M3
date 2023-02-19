
package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookshop/main")
    public String mainPage(Model model) {
        model.addAttribute("bookData", bookService.getRecommendedBooksData());
        return "index";
    }

    @GetMapping("/genres/index.html")
    public String genres() {
        return "genres";
    }

    @GetMapping("/genres/slug.html")
    public String detectives() {
        return "detectives";
    }

    @GetMapping("/authors/index.html")
    public String authors(Model model) {
        StringBuilder alph = new StringBuilder();
        for (int i = 65; i < 90; i++) {
            alph.append((char) i);
        }
        char[] alphabet = alph.toString().toCharArray();

        for (char ch : alphabet) {
            model.addAttribute("authorsTitle" + ch, bookService.getAuthorsTitle(ch));
        }
        return "authors";
    }

    @GetMapping("/authors/slug.html")
    public String borisVasilevichBedny() {
        return "borisVasilevichBedny";
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

}
