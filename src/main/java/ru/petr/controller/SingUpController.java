package ru.petr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.petr.entity.User;
import ru.petr.service.AuthoritiesService;
import ru.petr.service.UserService;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
public class SingUpController {
    private static final String JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";
    private static final String SING_UP_URL = "/sing-up";
    private static final String SING_UP_PAGE = "sing_up";
    private static final String SAVE_USER_URL = "/save-user";
    private static final String SING_IN_URL = "/sing-in";
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @GetMapping(SING_UP_URL)
    private String showSingUpPage() {
        return SING_UP_PAGE;
    }

    @PostMapping(path = SAVE_USER_URL, produces = JSON_CHARSET_UTF_8)
    @ResponseBody
    public Map<String, String> saveUser(@ModelAttribute User user, Locale locale) {
        User userFromStore = userService.findUserByName(user.getName());
        Map<String, String> response = new HashMap<>();
        if (!Objects.nonNull(userFromStore)) {
            userService.save(user);
            authoritiesService.createNewAuthorityByUserName(user.getName());
            response.put("url", SING_IN_URL);
        } else {
            response.put("error", messageSource.getMessage("image.not.empty", null, locale));
        }
        return response;
    }
}
