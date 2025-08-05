package com.authguard.authguard_client_service.Config;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.authguard.authguard_client_service.Service.ClientService;

@Component
public class config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DaoAuthenticationProvider clientAuthProvider(ClientService clientService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider(clientService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return daoProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(ClientService clientService) {
        return new ProviderManager(List.of(clientAuthProvider(clientService)));
    }

    @Bean
    public CacheManager redisChacheMangaer(RedisConnectionFactory connectionFatory) {
        Map<String, RedisCacheConfiguration> cacheMangaer = new HashMap<>();
        cacheMangaer.put("logedInUser",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).enableTimeToIdle());
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFatory))
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .withInitialCacheConfigurations(cacheMangaer).build();
    }

}
