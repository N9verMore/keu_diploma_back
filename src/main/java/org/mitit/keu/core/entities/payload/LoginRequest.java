package org.mitit.keu.core.entities.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class LoginRequest {
    private MultipartFile ecp;
    private String password;
}
