package com.edu.skillcity.config;

@org.springframework.context.annotation.Configuration
public class CustomConstants {
   // Path to store static files
   public final static String uploadPathFull = System.getenv("SKILLCITY_UPLOADS");
   // # Credentials for JWT
   public final static String adminUsr       = System.getenv("SKILLCITY_ADMIN_USER");
   public final static String adminPwd       = System.getenv("SKILLCITY_ADMIN_PASSWORD");
}
