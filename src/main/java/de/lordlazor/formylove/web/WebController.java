package de.lordlazor.formylove.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class WebController {

    private void saveCookie(HttpServletResponse response, String name, Object value){
        Cookie cookie = new Cookie(name, value.toString());
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @PostMapping("/ichliebedichkyra")
    public String loveSite(){
        return "love.html";
    }

    @PostMapping("/passwortfuerdieliebe")
    public String goToPasswort(){
        return "passwort.html";
    }

    @PostMapping("/pictures")
    public String pictureOfUs(HttpServletResponse response, String eingabe, @CookieValue(name = "passwortCookie", defaultValue = "empty") String passwortCookie){
        if(eingabe.equals("18.06.2022")){
            saveCookie(response, "passwortCookie", eingabe);
            return "picture.html";
        }
        return "passwort.html";
    }
    @GetMapping("/pictures")
    public String getPictureOfUs(@CookieValue(name = "passwortCookie", defaultValue = "empty") String passwortCookie){
        if(passwortCookie.equals("18.06.2022")){
            return "picture.html";
        }
        return "passwort.html";
    }

}