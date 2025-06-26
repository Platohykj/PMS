package org.example.register.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "register")
@Data
public class RegisterProperties {
    String passwordLengthMax;
}
