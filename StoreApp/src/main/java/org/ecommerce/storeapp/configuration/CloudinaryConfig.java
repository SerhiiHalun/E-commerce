package org.ecommerce.storeapp.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig{
        @Bean
        public Cloudinary cloudinary() {
            return new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dbwplsb0f",
                    "api_key", "436166771293576",
                    "api_secret", "kmRr0xA8wlUrDcUiN4xLQUx2fv0",
                    "secure", true
            ));
        }
}