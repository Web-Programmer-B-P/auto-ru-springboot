package ru.petr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.petr.entity.Advertisement;
import ru.petr.entity.Status;
import ru.petr.entity.User;
import ru.petr.service.AdvertisementService;
import ru.petr.service.ImageService;
import ru.petr.service.UserService;
import ru.petr.utils.Utils;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class AdvertisementController {
    private static final String JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";
    private static final String ALL_ADVERTISEMENTS = "/all-advertisements";
    private static final String LAST_ADVERTISEMENTS = "/get-last";
    private static final String CRITERION_ADVERTISEMENT = "/get-criterion";
    private static final String SINGLE_ADVERTISEMENT = "/advertisement";
    private static final String ATTRIBUTE_NAME_ADVERTISEMENT = "advertisement";
    private static final String FORM_CREATE = "create";
    private static final String CREATE_URL = "/create";
    private static final String MAIN_PAGE_URL = "/";
    private static final String SINGLE_PAGE_VIEW = "advertisement";
    private static final String URL_REDIRECT_TO_MAIN_PAGE = "redirect:/";
    private static final String CHANGE_STATUS_URL = "/status";
    private static final String INDEX_PAGE = "index";
    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(MAIN_PAGE_URL)
    public String showList() {
        return INDEX_PAGE;
    }

    @GetMapping(SINGLE_ADVERTISEMENT)
    public String showSinglePage(@RequestParam("id") int id, Model model) {
        Advertisement advertisement = advertisementService.findById(id);
        User user = userService.findById(advertisement.getUserId());
        model.addAttribute(ATTRIBUTE_NAME_ADVERTISEMENT, advertisement);
        model.addAttribute("phone", Utils.parsePhoneNumber(user.getPhone()));
        model.addAttribute("show", false);
        if (Utils.getSessionUserName().equals(user.getName())) {
            model.addAttribute("show", true);
        }
        return SINGLE_PAGE_VIEW;
    }

    @GetMapping(CREATE_URL)
    public String showCreateForm(Model model) {
        model.addAttribute(ATTRIBUTE_NAME_ADVERTISEMENT, new Advertisement());
        return FORM_CREATE;
    }

    @PostMapping(CREATE_URL)
    public String creationFormProcessing(@Valid @ModelAttribute(ATTRIBUTE_NAME_ADVERTISEMENT) Advertisement advertisement,
                                         Errors errors, @RequestParam("image") MultipartFile file,
                                         Model model, Locale locale) {

        String result = URL_REDIRECT_TO_MAIN_PAGE;
        if (errors.hasErrors() || file.isEmpty()) {
            model.addAttribute("error", messageSource.getMessage("image.not.empty", null, locale));
            result = FORM_CREATE;
        } else {
            int advertisementId = advertisementService.save(advertisement);
            imageService.saveImage(Utils.imageUpload(file, advertisementId));
        }
        return result;
    }

    @PostMapping(path = ALL_ADVERTISEMENTS, produces = JSON_CHARSET_UTF_8)
    @ResponseBody
    public List<Advertisement> getList() {
        return advertisementService.findAll();
    }

    @PostMapping(path = LAST_ADVERTISEMENTS, produces = JSON_CHARSET_UTF_8)
    @ResponseBody
    public List<Advertisement> getLast() {
        return advertisementService.findByLastDate();
    }

    @PostMapping(path = CRITERION_ADVERTISEMENT, produces = JSON_CHARSET_UTF_8)
    @ResponseBody
    public List<Advertisement> getCarByBodyName(@RequestParam("criterion") String carBody) {
        return advertisementService.findByCarBody(carBody);
    }

    @PostMapping(path = CHANGE_STATUS_URL, produces = JSON_CHARSET_UTF_8)
    @ResponseBody
    public void getMarkStatus(@ModelAttribute("status") Status status) {
        advertisementService.changeSaleStatus(status);
    }
}
