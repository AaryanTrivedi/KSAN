package com.kisan.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.kisan.custom_exceptions.ResourceNotFoundException;
import com.kisan.dao.ProductDao;
import com.kisan.dao.UserDao;
import com.kisan.dto.ApiResponse;
import com.kisan.dto.ProductRequestDto;
import com.kisan.dto.ProductResponseDto;
import com.kisan.dto.ProductsDto;
import com.kisan.dto.SellProductRequestDto;
import com.kisan.models.FarmingType;
import com.kisan.models.MetricType;
import com.kisan.models.Products;
import com.kisan.models.UserEntity;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ApiResponse addProduct(ProductRequestDto dto, MultipartFile productImage, Long userId) {
        // Fetch user from DB
        UserEntity user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Products product = new Products();
        product.setProductName(dto.productName());
        product.setProductType(dto.productType());
        product.setTotalStock(dto.totalStock());
        product.setMetric(dto.metric());
        product.setLandArea(dto.landArea());
        product.setFarmingType(dto.farmingType());
        product.setPrice(0);
        product.setStockToSell(0);
        product.setUser(user); 
        if (productImage != null && !productImage.isEmpty()) {
            try {
                product.setProductImage(productImage.getBytes());
                product.setImageName(productImage.getName());
                product.setImageType(productImage.getContentType());
            } catch (Exception e) {
//                return new ApiResponse("Failed to upload product image: " + e.getMessage());
                return null;
            }
        }
        productDao.save(product);

//        return new ApiResponse("Added new product with ID " + product.getId());
        return null;
    }
    
    @Override
    public byte[] getProductImage(Long id) {
    	Products product = productDao.findById(id).orElseThrow();
    	return product.getProductImage();
    }
    
    @Override
    public String getImageType(Long id) {
    	Products product = productDao.findById(id).orElseThrow();
    	return product.getImageType();
    }

    
    @Override
    public Object updateProduct(Long id, ProductRequestDto productDto, MultipartFile productImage) {
        Products product = productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        product.setProductName(productDto.productName());
        product.setProductType(productDto.productType());
        product.setTotalStock(productDto.totalStock());
        product.setMetric(productDto.metric());
        product.setLandArea(productDto.landArea());

        if (productImage != null && !productImage.isEmpty()) {
        	try {
                product.setProductImage(productImage.getBytes());
                product.setImageName(productImage.getName());
                product.setImageType(productImage.getContentType());
            } catch (Exception e) {
//                return new ApiResponse("Failed to upload product image: " + e.getMessage());
                    return  null;
            }
        }
        productDao.save(product);
//        return new ApiResponse("Product updated successfully");
        return null;
    }


    @Override
    public ApiResponse deleteProductDetails(Long productId) {
        Products product = productDao.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setStatus(false);
        productDao.save(product);
//        return new ApiResponse("Product deleted successfully");
        return null;
    }

    @Override
    public List<ProductResponseDto> getUserProducts(Long userId) {
        List<Products> products = productDao.findByUserId(userId);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProducts(int pageNumber, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<Products> products = productDao.findAll(pageable);
    	return products.getContent().stream().map(this::convertToDto).collect(Collectors.toList());

    }


    @Override
    public ProductResponseDto getAProduct(Long id) {
        Products product = productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return convertToDto(product);
    }

    @Override
    public ApiResponse markForSale(SellProductRequestDto dto, Long id) {
        Products product = productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setStockToSell(dto.stockToSell());
        product.setPrice(dto.price());
        product.setMarkedForSale(true);

        productDao.save(product);
//        return new ApiResponse("Product marked for sale");
        return null;
    }

    @Override
    public List<ProductResponseDto> getProductsByFarmingType(FarmingType farmingType) {
        List<Products> products = productDao.findByFarmingType(farmingType);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    private ProductResponseDto convertToDto(Products product) {
        return new ProductResponseDto(
                product.getProductName(),
                product.getProductType().toString(),
                product.getPrice(),
                product.getTotalStock(),
                product.getStockToSell(),
                product.isMarkedForSale(),
                product.getMetric(),
                product.getFarmingType().toString(),
                product.getLandArea()    
        );
    }
    
    
    @Override
    public List<ProductsDto> getProductsMarkedForSale() {
    	List<Products> products = productDao.findByMarkedForSaleTrue();
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
    
    private ProductsDto convert(Products products) {
    	return new ProductsDto(
    			products.getId(),
    			products.getProductName(),
    			products.getPrice());
    }
}
    






