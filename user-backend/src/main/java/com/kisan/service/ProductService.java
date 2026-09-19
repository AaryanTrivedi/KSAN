package com.kisan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kisan.dto.ApiResponse;
import com.kisan.dto.ProductRequestDto;
import com.kisan.dto.ProductResponseDto;
import com.kisan.dto.ProductsDto;
import com.kisan.dto.SellProductRequestDto;
import com.kisan.models.FarmingType;

public interface ProductService {


    List<ProductResponseDto> getUserProducts(Long userId);

    ProductResponseDto getAProduct(Long id);

    List<ProductResponseDto> getProductsByFarmingType(FarmingType farmingType);

	List<ProductResponseDto> getAllProducts(int pageNumber, int pageSize);

	String getImageType(Long id);

    byte[] getProductImage(Long id);

    Object updateProduct(Long id, ProductRequestDto productDto, MultipartFile productImage);
    ApiResponse addProduct(ProductRequestDto dto, MultipartFile productImage, Long userId);
    ApiResponse deleteProductDetails(Long productId);
    ApiResponse markForSale(SellProductRequestDto dto, Long id);
    List<ProductsDto> getProductsMarkedForSale();
}
