package fr.ippon.tatami.web.controller;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.security.AuthenticationService;
import fr.ippon.tatami.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

/**
 * @author Julien Dubois
 */
@Controller
public class AccountThemeController {

    private final Log log = LogFactory.getLog(AccountThemeController.class);

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/account/theme",
            method = RequestMethod.GET)
    public ModelAndView getTheme() {
        ModelAndView mv = new ModelAndView("account_theme");
        User currentUser = authenticationService.getCurrentUser();
        mv.addObject("user", userService.getUserByLogin(currentUser.getLogin()));
        mv.addObject("theme", currentUser.getTheme());
        return mv;
    }

    @RequestMapping(value = "/account/theme",
            method = RequestMethod.POST)
    public String updateTheme(@RequestParam String theme) {
        userService.updateTheme(theme);
        return "redirect:/tatami/account/theme";
    }
}