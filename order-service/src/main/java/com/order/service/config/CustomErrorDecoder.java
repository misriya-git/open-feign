package com.order.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.order.service.exception.BadRequestException;
import com.order.service.exception.InternalServerErrorException;
import com.order.service.exception.ProductNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                return new BadRequestException("Bad Request!");
            case 404:
                return new ProductNotFoundException("Product Not Found!");
            case 500:
                return new InternalServerErrorException("Internal Server Error!");
            default:
                return new Exception("Generic exception");
        }
    }
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
    
}
