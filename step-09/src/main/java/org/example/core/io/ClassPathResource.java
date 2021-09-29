package org.example.core.io;

import org.example.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{
    private String classPath;

    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path,null);
    }

    public ClassPathResource(String path,ClassLoader classLoader){
        this.classPath=path;
        this.classLoader =(classLoader!=null ?classLoader : ClassUtils.getDefaultClassLoader());
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(classPath);
        if(is == null){
            throw new FileNotFoundException(
                    this.classPath + " cannot be opened because it does not exist");
        }

        return is;
    }
}
