package spring.core.io;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 定义资源
 */
public interface Resource {

InputStream getInputStream() throws IOException;
}
