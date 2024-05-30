package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.repository.UserServiceDao;
import lk.helloshoe.hsplbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    final private UserServiceDao userDao;
    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDao.findByEmail(username).
                        orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
