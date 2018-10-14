package com.test.listener;

import com.test.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
    private final Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    @Autowired
    private AbstractRepository abstractRepository;

    @Autowired
    Environment environment;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        final String[] profiles  = environment.getActiveProfiles();
        if(profiles.length > 0){
            return;
        }
        logger.info("∂Done User Details.");

    }
}
