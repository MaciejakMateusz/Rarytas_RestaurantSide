package pl.rarytas.rarytas_restaurantside.controller.restaurant;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rarytas.rarytas_restaurantside.entity.Category;
import pl.rarytas.rarytas_restaurantside.entity.User;
import pl.rarytas.rarytas_restaurantside.service.CategoryService;
import pl.rarytas.rarytas_restaurantside.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class MainViewController {
    private final OrderService orderService;
    private final CategoryService categoryService;

    public MainViewController(OrderService orderService,
                              CategoryService categoryService) {
        this.orderService = orderService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String mainView(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "restaurant/main-view";
    }

    @GetMapping("/menu")
    public String menu() {
        return "/restaurant/menu";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @PostMapping
    public String update(@RequestParam Integer id,
                         @RequestParam boolean paid) {
        orderService.finish(id, paid);
        return "restaurant/main-view";
    }

    @ModelAttribute("categories")
    private List<Category> getEntireMenu() {
        return categoryService.findAll();
    }

}