package in.ksan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import in.ksan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import in.ksan.exceptions.ResourceNotFoundException;
import in.ksan.repositories.ProductRepository;
import in.ksan.repositories.UserRepository;
import in.ksan.dto.ApiResponse;
import in.ksan.dto.ProductRequestDto;
import in.ksan.dto.ProductResponseDto;
import in.ksan.dto.ProductsDto;
import in.ksan.dto.SellProductRequestDto;
import in.ksan.models.FarmingType;
import in.ksan.models.Products;
import in.ksan.models.UserEntity;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            UserRepository userRepository
    ){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse addProduct(ProductRequestDto dto, MultipartFile productImage, Long userId) {
        // Fetch user from DB
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
        productRepository.save(product);

//        return new ApiResponse("Added new product with ID " + product.getId());
        return null;
    }
    
    @Override
    public byte[] getProductImage(Long id) {
    	Products product = productRepository.findById(id).orElseThrow();
    	return product.getProductImage();
    }
    
    @Override
    public String getImageType(Long id) {
    	Products product = productRepository.findById(id).orElseThrow();
    	return product.getImageType();
    }

    
    @Override
    public Object updateProduct(Long id, ProductRequestDto productDto, MultipartFile productImage) {
        Products product = productRepository.findById(id)
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
        productRepository.save(product);
//        return new ApiResponse("Product updated successfully");
        return null;
    }


    @Override
    public ApiResponse deleteProductDetails(Long productId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setStatus(false);
        productRepository.save(product);
//        return new ApiResponse("Product deleted successfully");
        return null;
    }

    @Override
    public List<ProductResponseDto> getUserProducts(Long userId) {
        List<Products> products = productRepository.findByUserId(userId);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProducts(int pageNumber, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<Products> products = productRepository.findAll(pageable);
    	return products.getContent().stream().map(this::convertToDto).collect(Collectors.toList());

    }


    @Override
    public ProductResponseDto getAProduct(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return convertToDto(product);
    }

    @Override
    public ApiResponse markForSale(SellProductRequestDto dto, Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setStockToSell(dto.stockToSell());
        product.setPrice(dto.price());
        product.setMarkedForSale(true);

        productRepository.save(product);
//        return new ApiResponse("Product marked for sale");
        return null;
    }

    @Override
    public List<ProductResponseDto> getProductsByFarmingType(FarmingType farmingType) {
        List<Products> products = productRepository.findByFarmingType(farmingType);
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
    	List<Products> products = productRepository.findByMarkedForSaleTrue();
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
    
    private ProductsDto convert(Products products) {
    	return new ProductsDto(
    			products.getId(),
    			products.getProductName(),
    			products.getPrice());
    }
}
    






