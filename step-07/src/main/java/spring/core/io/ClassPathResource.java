package spring.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassPathResource implements  Resource{

    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,null);
    }
    public ClassPathResource (String path,ClassLoader classLoader){
        Assert.notNull(path,"path must not be null");
        this.path=path;
        this.classLoader = classLoader != null ?classLoader: ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
      InputStream is = classLoader.getResourceAsStream(path);
      if(is==null){
          throw new FileNotFoundException(
                  this.path + " cannot be opened because it does not exist");
      }

    return is;
    }

}
