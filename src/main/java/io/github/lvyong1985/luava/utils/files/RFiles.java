package io.github.lvyong1985.luava.utils.files;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/20
 * @since 1.0
 **/
public class RFiles {

  public static String toString(File file) throws IOException {
    return toString(file, Charset.defaultCharset());
  }

  public static String toString(File file, Charset charset) throws IOException {
    return new String(Files.readAllBytes(file.toPath()), charset);
  }

  public static File getFileFromClassPath(String path) {
    URL resource = RFiles.class.getResource(path);
    if (resource == null) {
      return null;
    }
    return new File(resource.getPath());
  }

}
