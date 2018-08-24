package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.RoleService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private BillService billService;

    @Autowired
    public AdminController(UserService userService, BillService billService) {
        this.userService = userService;
        this.billService = billService;
    }

    @PostMapping("users/create/")
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        userService.update(userId, user);
    }

    @GetMapping("users/")
    public List<User> getAllUsers(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        System.out.println();
        return userService.getAll();
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userService.delete(userId);
    }

    @PostMapping("bills/create/")
    public void createBill(@RequestBody Bill bill) {
        billService.createBill(bill);
    }
}