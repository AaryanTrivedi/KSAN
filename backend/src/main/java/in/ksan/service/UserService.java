package in.ksan.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.ksan.dto.ApiResponse;
import in.ksan.dto.ChangePasswordDto;
import in.ksan.dto.RegisterUserDto;
import in.ksan.dto.UpdateProfileDto;
import in.ksan.dto.UserDto;
import in.ksan.dto.UserListDto;
import in.ksan.dto.UserResponseDto;
import in.ksan.models.FarmingType;
import in.ksan.models.UserRole;

public interface UserService {

	ApiResponse deleteUser(Long userId);

	List<UserListDto> getUsersByRole(UserRole role, int pageNumber, int pageSize);

	List<UserListDto> getUsersByFarmingType(FarmingType type, int pageNumber, int pageSize);

	UserDto getUserById(Long userId);

	List<UserListDto> getAllUsers(int pageNumber, int pageSize);

	ApiResponse changePassword(ChangePasswordDto dto);

	ApiResponse updateProfile(UpdateProfileDto dto);

//	ApiResponse registerNewUser(UserDto dto, MultipartFile image);

	UserResponseDto getUserByJwt();

	ApiResponse addNewUser(RegisterUserDto user, MultipartFile imageFile) throws IOException;
	
	byte[] getUserImage(Long id);
	
	String getImageType(Long id);

	
}