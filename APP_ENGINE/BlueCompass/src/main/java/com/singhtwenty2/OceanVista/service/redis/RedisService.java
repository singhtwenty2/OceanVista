package com.singhtwenty2.OceanVista.service.redis;

import com.singhtwenty2.OceanVista.data.model.dto.request.RegisterRequestDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveUserToken(String token, RegisterRequestDTO userDetails) {
        redisTemplate.opsForValue().set(token, userDetails, Duration.ofMinutes(30));
    }

    public RegisterRequestDTO getUserDetailsByToken(String token) {
        return (RegisterRequestDTO) redisTemplate.opsForValue().get(token);
    }

    public void deleteUserToken(String token) {
        redisTemplate.delete(token);
    }
}
