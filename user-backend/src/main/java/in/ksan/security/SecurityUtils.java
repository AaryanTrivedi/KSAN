package in.ksan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import in.ksan.exceptions.ResourceNotFoundException;
import in.ksan.repositories.UserRepository;
import in.ksan.models.UserEntity;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getCredentials() == null) {
            throw new AccessDeniedException("User is not authenticated");
        }
        Long userId = (Long) authentication.getCredentials();
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
