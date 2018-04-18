package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${company.name}")
    private String companyName;

    @Value("${spring.mail.username}")
    private String companyMail;

    @Value("${company.phone}")
    private String companyPhone;

    @Value("${admin.sex}")
    private String adminSex;

    public String getAdminMail() {
        return adminMail;
    }
}
