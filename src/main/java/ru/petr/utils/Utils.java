package ru.petr.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import ru.petr.entity.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;

final public class Utils {
    private static final Logger LOG = LogManager.getLogger(Utils.class.getName());
    private static final String INVALID_READ_STREAM = "Смотри в создание нового изображения";
    private static final int NOT_VALID_ID = 0;
    private static final String REGEX_TO_REPLACE_BRACKETS_WITH_SPACES = "\\(|\\)";

    private Utils() {
    }

    public static String parsePhoneNumber(String originalPhone) {
        StringBuilder phoneWithOutBrackets = new StringBuilder();
        String[] res = originalPhone.split(REGEX_TO_REPLACE_BRACKETS_WITH_SPACES);
        phoneWithOutBrackets.append(res[0]).append(" ").append(res[1]).append(" ").append(res[2]);
        return phoneWithOutBrackets.toString();
    }

    public static Image imageUpload(MultipartFile image, int advertisementId) {
        Image readyImage = null;
        if (Objects.nonNull(image) && advertisementId != NOT_VALID_ID) {
            readyImage = new Image();
            try (BufferedInputStream inputStream = new BufferedInputStream(image.getInputStream())) {
                readyImage.setImage(inputStream.readAllBytes());
                readyImage.setName(image.getOriginalFilename());
                readyImage.setAdvertisementId(advertisementId);
            } catch (IOException io) {
                LOG.error(INVALID_READ_STREAM, io);
            }
        }
        return readyImage;
    }

    public static String getSessionUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
