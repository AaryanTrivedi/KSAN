package in.ksan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.ksan.dto.ApiResponse;
import in.ksan.dto.ProductRequestDto;
import in.ksan.dto.ProductResponseDto;
import in.ksan.dto.ProductsDto;
import in.ksan.dto.SellProductRequestDto;
import in.ksan.models.FarmingType;

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
