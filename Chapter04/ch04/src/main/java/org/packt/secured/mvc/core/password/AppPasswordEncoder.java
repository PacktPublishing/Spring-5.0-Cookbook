package org.packt.secured.mvc.core.password;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


public class AppPasswordEncoder {

    public String md5Encoder(String password, String salt) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        md5PasswordEncoder.setEncodeHashAsBase64(true);
        md5PasswordEncoder.setIterations(32);
        String encoded = md5PasswordEncoder.encodePassword(password,salt);
        return encoded;     
    }

   
    public String bcryptEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = bCryptPasswordEncoder.encode(password);
        return encoded;
    }

    
    public String standardEncoder(String password) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        String encoded = standardPasswordEncoder.encode(password);
        return encoded;
    }

}
