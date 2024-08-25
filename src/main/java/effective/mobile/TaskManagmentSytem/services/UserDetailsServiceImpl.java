package effective.mobile.TaskManagmentSytem.services;

import effective.mobile.TaskManagmentSytem.dto.UserDetailsImpl;
import effective.mobile.TaskManagmentSytem.exceptions.UserNotFoundException;
import effective.mobile.TaskManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsImpl result = UserDetailsImpl.build(userRepository.findByEmail(s)
                .orElseThrow(() -> new UserNotFoundException("User not found with email:" + s)));

        return result;
    }
}
