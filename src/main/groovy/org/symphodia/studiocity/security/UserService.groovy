package org.symphodia.studiocity.security
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.symphodia.studiocity.repository.UserRepository

@Service
class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository

    UserDetails loadUserByUsername(String email) {
        new SecurityUser(userRepository.findOneByEmail(email))
    }

}
