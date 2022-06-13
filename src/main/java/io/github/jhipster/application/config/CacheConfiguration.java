package io.github.jhipster.application.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            createCache(cm, io.github.jhipster.application.domain.Service.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Service.class.getName() + ".serviceData");
            createCache(cm, io.github.jhipster.application.domain.ServiceData.class.getName());
            createCache(cm, io.github.jhipster.application.domain.RestaurantMenu.class.getName());
            createCache(cm, io.github.jhipster.application.domain.RestaurantMenu.class.getName() + ".restaurantMenuData");
            createCache(cm, io.github.jhipster.application.domain.RestaurantMenuData.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Concierge.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Concierge.class.getName() + ".conciergeData");
            createCache(cm, io.github.jhipster.application.domain.ConciergeData.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Playlist.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }
}
