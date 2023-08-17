package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.Like;
import com.blog.azerbaijani.entity.Makale;
import com.blog.azerbaijani.entity.User;
import com.blog.azerbaijani.entity.Yorum;
import com.blog.azerbaijani.repository.LikeRepository;
import com.blog.azerbaijani.repository.MakaleRepository;
import com.blog.azerbaijani.repository.UserRepository;
import com.blog.azerbaijani.repository.YorumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MakaleRepository makaleRepository;

    @Autowired
    private YorumRepository yorumRepository;

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/makale")
    public String getMakale(Model model){
        return "makale_olustur";
    }
    @PostMapping("/makale")
    public String makaleOlustur(@RequestParam(name = "baslik")String baslik,
                                @RequestParam(name = "icerik")String icerik,
                                @RequestParam(name = "category") String tip,
                                Model model){
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        Makale makale = new Makale();
        makale.setMakaleTipi(tip);
        makale.setUser(user);
        makale.setBaslik(baslik);
        makale.setIcerik(icerik);
        makaleRepository.save(makale);
        model.addAttribute("makale",makale);
        return "redirect:makaleler";
    }
    @GetMapping("/makale-goster/{id}")
    public String makaleyiGoster(Model model,
                                 @PathVariable(name = "id") Integer makale_id) {
        int a = makale_id;
        model.addAttribute("makale", makaleRepository.getById(a));
        Makale makale = makaleRepository.getById(a);
        List<Yorum> yorumlar = yorumRepository.findByMakale(makale);
        model.addAttribute(yorumlar);
        model.addAttribute(makale.getLikes());
        return "makale_etkilesim";
    }

    @PostMapping("/add-comment")
    public String makaleEtkilesimiYorum(@RequestParam(name = "comment")String comment,
                                   @RequestParam(name = "makale_id")Integer makale_id,
                                        Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        Yorum yorum = new Yorum();
        yorum.setIcerik(comment);
        yorum.setUser(user);
        yorum.setMakale(makaleRepository.getById(makale_id));
        yorumRepository.save(yorum);
        model.addAttribute("makale", makaleRepository.getById(makale_id));
        return "makale_etkilesim";
    }

    @PostMapping("/like")
    public String makaleEtkilesimiBegeni(@RequestParam(name = "begeni")String begeni,
                                   @RequestParam(name = "makale_id")String makaleId,
                                         Model model) {
        int makale_id = Integer.parseInt(makaleId);
        Makale makale = makaleRepository.getById(makale_id);
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        boolean alreadyLiked = false;
        for (Like like : makale.getLikes()) {
            if (like.getUser().getUsername().equals(user.getUsername())) {
                System.out.println("isledi"
                );
                alreadyLiked = true;
                break;
            }
        }

        if (!alreadyLiked) {
            int a;
            if(makale.getBegeniSayisi()==null){
                a=0;
            }else{
                a=makale.getBegeniSayisi();
            }
            a++;
            Like like = new Like();
            like.setUser(user);
            like.setMakale(makale);

            makale.getLikes().add(like);
            makale.setBegeniSayisi(a);

            likeRepository.save(like);
            makaleRepository.save(makale);
        }

        model.addAttribute("makale", makale);

        return "makale_etkilesim";
    }
    @GetMapping("/makaleler")
    public String getAllTheMakale(Model model){
        List<Makale> makaleler = makaleRepository.findAll();
        model.addAttribute("makaleList", makaleler);
        return "makaleler";
    }
    @GetMapping("/register")
    public String registrationPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model){
        for(User u : userRepository.findAll()){
            if(u.getUsername().equals(user.getUsername())){
                model.addAttribute("usernameError", "Username already exists");
                return "register";
            }else if(u.getEmail().equals(user.getEmail())){
                model.addAttribute("emailError", "Email already exists");
                return "register";
            }
        }
        userRepository.save(user);
        return "redirect:register?success";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("alreadyLoggedIn", false);
        } else {
            model.addAttribute("alreadyLoggedIn", true);
        }
        return "login";
    }
    @GetMapping("/search")
    public String searchPage(@RequestParam(name="category")String sorgu,
                             Model model){
        List<Makale> makaleler = new ArrayList<>();
        for(Makale makale : makaleRepository.findAll()){
            if(makale.getMakaleTipi().equals(sorgu)){
                makaleler.add(makale);
            }
        }
        model.addAttribute("makaleler", makaleler);
        return "search";
    }

}






