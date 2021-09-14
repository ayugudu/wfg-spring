package org.wfg.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源类型 策略模式
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
