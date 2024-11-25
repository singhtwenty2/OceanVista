package com.singhtwenty2.OceanVistaBusiness.service.redis;

import com.singhtwenty2.OceanVistaBusiness.data.model.dto.request.RegisterPartnerRequestDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void savePartnerToken(String token, RegisterPartnerRequestDTO partnerRequestDTO) {
        redisTemplate.opsForValue().set(token, partnerRequestDTO, Duration.ofMinutes(30));
    }

    public RegisterPartnerRequestDTO getPartnerDetailsByToken(String token) {
        return (RegisterPartnerRequestDTO) redisTemplate.opsForValue().get(token);
    }

    public void deletePartnerToken(String token) {
        redisTemplate.delete(token);
    }
}
