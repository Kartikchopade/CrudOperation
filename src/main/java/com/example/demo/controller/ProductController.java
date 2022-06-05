package com.example.demo.controller;

import com.example.demo.repository.ProductRepository;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController 
{
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/product")
    public String product(Model model)
    {
        model.addAttribute("products",productRepository.findAll());
        return "product";
    }
    
    @GetMapping("/product/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product) throws ServletException, IOException
    {		
	product = productService.getProductById(id);
	response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(product.get().getProdImage1());        
	response.getOutputStream().close();
    }
    
    @GetMapping("/product/video/{id}")
    @ResponseBody
    void showVideo(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product) throws ServletException, IOException
    {		
	product = productService.getProductById(id);
	response.setContentType("video/mp4,video/ogg");
        response.getOutputStream().write(product.get().getProdVideo());        
	response.getOutputStream().close();
    }
    
    @RequestMapping("/create")
    public String create()
    {
        return "create";
    }
    
    @RequestMapping("/save")
    public String save(@RequestParam String prodName,@RequestParam String prodDesc,@RequestParam Double prodPrice,@RequestParam String prodImage,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date prodDate,@RequestParam("prodImage1") MultipartFile file,@RequestParam("prodVideo") MultipartFile file1) throws IOException
    {
        byte[] imageData = file.getBytes();
        byte[] videoData = file1.getBytes();
        Product product=new Product();
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        product.setProdDate(prodDate);
        product.setProdImage1(imageData);
        product.setProdVideo(videoData);
        productRepository.save(product);
        return "redirect:/show/"+product.getId();
    }
    
    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id,Model model)
    {
        model.addAttribute("product",productRepository.findById(id).orElse(null));
        return "show";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam Long id)
    {
        Product product=productRepository.findById(id).orElse(null);
        productRepository.delete(product);
        return "redirect:/product";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model)
    {
        model.addAttribute("product",productRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id,@RequestParam String prodName,@RequestParam String prodDesc,@RequestParam Double prodPrice,@RequestParam String prodImage,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date prodDate,@RequestParam("prodImage1") MultipartFile file) throws IOException
    {
        byte[] imageData = file.getBytes();
        Product product=productRepository.findById(id).orElse(null);
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        product.setProdDate(prodDate);
        product.setProdImage1(imageData);
        productRepository.save(product);
        return "redirect:/show/"+product.getId();        
    }
    @GetMapping("getProductbyname")
    public String findByprodName(@RequestParam String name,Model m)
    {
        m.addAttribute("products",productRepository.findByprodName(name));
        return "product";	
    }
    
    @GetMapping("getProductbydate")    
    public String findByprodDateBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate,Model m)
    {
        m.addAttribute("products",productRepository.findByprodDateBetween(startdate, enddate));
        return "product";	
    }
    
}
