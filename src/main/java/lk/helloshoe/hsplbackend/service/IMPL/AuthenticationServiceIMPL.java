package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.Util.UtilMatters;
import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.UserDTO;
import lk.helloshoe.hsplbackend.entity.UserEntity;
import lk.helloshoe.hsplbackend.repository.UserServiceDao;
import lk.helloshoe.hsplbackend.secureAndResponse.response.JwtAuthResponse;
import lk.helloshoe.hsplbackend.secureAndResponse.secure.SignIn;
import lk.helloshoe.hsplbackend.secureAndResponse.secure.SignUp;
import lk.helloshoe.hsplbackend.service.AuthenticationService;
import lk.helloshoe.hsplbackend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {

    private final ConversionData conversionData;
    private final UserServiceDao userDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthResponse signUp(SignUp signUp) {
        UserDTO userDTO = UserDTO.builder()
                .id(UtilMatters.generateId())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(signUp.getRole())
                .build();
        UserEntity saveUser = userDao.save(conversionData.toUserEntity(userDTO));
        String generateToken = jwtService.generateToken(saveUser);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword())
        );
        UserEntity user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generateToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        var userEntity = userDao
                .findByEmail(jwtService.extractUserName(refreshToken))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return JwtAuthResponse.builder().
                token(jwtService.generateToken(userEntity)).build();
    }
}
