package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.ProductFromTypeDto;
import by.tms.onlinerclonec30onl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/catalog")
public class ProductController {
    private ProductService productService;
    // удалить после создания Регистрации профиля!!!
    @Autowired
    private AccountDAO accountDAO;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{idProduct}")
    public String index(@PathVariable(value = "idProduct") Long idProduct, Model model) {
        model.addAttribute("productDTO", productService.getProductPageData(idProduct));
        // удалить после создания Регистрации профиля!!!
        model.addAttribute("CurrentUser",accountDAO.findByID(1).get());
        return "product";
    }

    @GetMapping("/product")
    public String productType(@RequestParam("id") Long id, Model model) {
        List<ProductType> productTypes = productService.getAllProductTypes();
        Optional<ProductType> productType = productService.getProductTypeById(id);
        productType.ifPresent(type -> model.addAttribute("title", type.getTypeName()));
        List<ProductFromTypeDto> products = productService.getAllProductsFromTypeDto(id);
        model.addAttribute("allTypes", productTypes);
        model.addAttribute("products", products);
        return "product_type";
    }

    @PostMapping("/product/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<ProductFromTypeDto> products = productService.searchProductsFromTypeDto(search);
        model.addAttribute("products", products);
        return "searchProduct";
    }

}
