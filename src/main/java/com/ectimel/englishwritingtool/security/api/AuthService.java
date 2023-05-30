package com.ectimel.englishwritingtool.security.api;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
