package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private HttpSession session;
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert.html";
    }
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "/administrator/login";
    }
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        String mailAddress = form.getMailAddress();
        String password = form.getPassword();
        Administrator administrator = administratorService.login(mailAddress, password);
        if(administrator==null) {
            String errorMessage = "メールアドレスまたはパスワードが不正です";
            model.addAttribute("errorMessage",errorMessage);
            return "/administrator/login";
        }
        String administratorName = administrator.getName();
        session.setAttribute("administratorName", administratorName);
        return "redirect:/employee/showList";
    }

}
