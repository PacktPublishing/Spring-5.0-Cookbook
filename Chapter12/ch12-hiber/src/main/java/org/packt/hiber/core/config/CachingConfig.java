package org.packt.hiber.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public Config hazelCastConfig() {
 
        Config config = new Config();
        config.setInstanceName("hazelcast-packt-cache");
        config.setProperty("hazelcast.jmx", "true");
 
        MapConfig deptCache = new MapConfig();
        deptCache.setTimeToLiveSeconds(20);
        deptCache.setEvictionPolicy(EvictionPolicy.LFU);
       
        config.getMapConfigs().put("hazeldept",deptCache);
        return config;
    }
    
    @Bean
    public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
        return Hazelcast.newHazelcastInstance(hazelCastConfig);
    }
 
    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }
}
