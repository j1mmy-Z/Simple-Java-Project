package com.jimmy.controller;

import com.jimmy.domain.Product;
import com.jimmy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("productList",products);
        modelAndView.setViewName("product-list");
        for (Product product : products) {
            System.out.println(product);
        }
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
