package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.ProductFromTypeDto;
import by.tms.onlinerclonec30onl.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{idProduct}")
    public String index(@PathVariable(value = "idProduct") Long idProduct, Model model) {
        model.addAttribute("productDTO", productService.getProductPageData(idProduct));

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
}
