package in.ksan.service.impl;

import java.io.IOException;
import java.util.List;

import in.ksan.dto.*;
import in.ksan.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import in.ksan.exceptions.ApiException;
import in.ksan.exceptions.ResourceNotFoundException;
import in.ksan.repositories.UserRepository;
import in.ksan.models.FarmingType;
import in.ksan.models.UserEntity;
import in.ksan.models.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final ImageHandlingService imageHandlingService;

    public UserServiceImpl(	UserRepository userRepository,
    						PasswordEncoder passwordEncoder
    						//, ImageHandlingService imageHandlingService
    						) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
       // this.imageHandlingService = imageHandlingService;
    }


    @Override
	public ApiResponse addNewUser(RegisterUserDto dto, MultipartFile imageFile) throws IOException {
		 if (userRepository.existsByEmail(dto.email())) {
	            throw new ApiException("User email already exists!");
	        }

	        UserEntity user = new UserEntity();
	        user.setFirstName(dto.firstName());
	        user.setLastName(dto.lastName());
	        user.setEmail(dto.email());
	        user.setPassword(passwordEncoder.encode(dto.password()));
	        user.setMobile(dto.mobile());
	        user.setFarmingType(dto.farmingType());
	        user.setAdrLine1(dto.adrLine1());
	        user.setAdrLine2(dto.adrLine2());
	        user.setCity(dto.city());
	        user.setGender(dto.gender());
	        user.setState(dto.state());
	        user.setZipCode(dto.zipCode());
	        user.setRole(dto.role());
	        user.setStatus(true);
	        user.setImageName(imageFile.getName());
	        user.setImageType(imageFile.getContentType());
	        user.setProfileImage(imageFile.getBytes());
		
		UserEntity savedUser =  userRepository.save(user);
		return new ApiResponse(null,"");
	}
    
    @Override
    public byte[] getUserImage(Long id) {
    	UserEntity user = userRepository.findById(id).orElseThrow();
    	return user.getProfileImage();
    }
    
    @Override
    public String getImageType(Long id) {
    	UserEntity user = userRepository.findById(id).orElseThrow();
    	return user.getImageType();
    }

    

    @Override
    public ApiResponse updateProfile(UpdateProfileDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.save(user);
        return new ApiResponse(null, null);
    }
 
    
    
    @Override
    public ApiResponse changePassword(ChangePasswordDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())) {
            throw new ApiException("Incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
        return new ApiResponse(null,"");
    }
    
    
    
    
    @Override
	public List<UserListDto> getAllUsers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<UserEntity> users = userRepository.findAll(pageable).getContent();
		return null;
	}
    
    
    
    
    
    
    @Override
    public UserDto getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return null;
    }

    
    
    
    
    @Override
    public List<UserListDto> getUsersByFarmingType(FarmingType type, int pageNumber, int pageSize) {
//        return userDao.findByFarmingType(type).stream().map(user -> modelMapper.map(user, UserListDto.class)).collect(Collectors.toList());
        return null;
    }

    
    
    
    @Override
    public List<UserListDto> getUsersByRole(UserRole role, int pageNumber, int pageSize) {
//        return userDao.findByRole(role).stream().map(user -> modelMapper.map(user, UserListDto.class)).collect(Collectors.toList());
        return null;
    }






    @Override
    public ApiResponse deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
//        return new ApiResponse("User soft deleted successfully");
        return null;
    }








	@Override
	public UserResponseDto getUserByJwt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found"));
//		return modelMapper.map(user, UserResponseDto.class);
        return null;
	}


}
