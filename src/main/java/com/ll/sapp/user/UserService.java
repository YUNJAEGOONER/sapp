package com.ll.sapp.user;

import com.ll.sapp.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);

        //비밀번호 암호화 -> 비크립트
        //해시 함수의 하나로 주로 비밀번호와 같은 보안 정보를 안전하게 저장하고 검증할 때 사용
        user.setPassword(passwordEncoder.encode(password)); //암호화된 비밀번호를 사용자의 비밀번호로 설정
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if(siteUser.isPresent()){
            return siteUser.get();
        }
        else{
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
