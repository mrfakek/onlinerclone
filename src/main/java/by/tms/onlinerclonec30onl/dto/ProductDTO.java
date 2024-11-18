package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Product product;
    private ProductPhoto photo;
    private ProductShopDTO chooseShop;
    List<ProductShopDTO> productShopDTOList;
}
