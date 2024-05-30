package lk.helloshoe.hsplbackend.service;


import lk.helloshoe.hsplbackend.secureAndResponse.response.JwtAuthResponse;
import lk.helloshoe.hsplbackend.secureAndResponse.secure.SignIn;
import lk.helloshoe.hsplbackend.secureAndResponse.secure.SignUp;

public interface AuthenticationService {
    JwtAuthResponse signUp(SignUp signup);
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse refreshToken(String refreshToken);
}
