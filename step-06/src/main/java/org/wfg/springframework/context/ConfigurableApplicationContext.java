package org.wfg.springframework.context;

/**
 * 应用上下文的实现
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
/**
 * refresh
 */

void refresh() throws Exception;

}
