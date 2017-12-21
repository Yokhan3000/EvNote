package com.epam.evnote.config;

import com.epam.evnote.aspects.ServiceMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Mikhail Chtetsov on 20/12/2017.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages="com.epam.evnote.service")
public class MonitorConfig {

}
