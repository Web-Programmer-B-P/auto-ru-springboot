package ru.petr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.petr.entity.User;

@Controller
public class SingInController {
    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_PAGE_URL = "/sing-in";

    @GetMapping(LOGIN_PAGE_URL)
    public String showSingInForm(Model model) {
        model.addAttribute("user", new User());
        return LOGIN_PAGE;
    }
}
