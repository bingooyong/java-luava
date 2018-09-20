package io.github.lvyong1985.luava.utils.files;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/20
 * @since 1.0
 **/
public class RFilesTest {

  @Test
  public void testToString() throws Exception {
    String content = RFiles.toString(RFiles.getFileFromClassPath("/file_utf_8.txt"));
    String expect = "测试读取文件1\n"
        + "测试读取文件2\n"
        + "测试读取文件3\n"
        + "测试读取文件4\n"
        + "测试读取文件5\n";
    Assert.assertEquals(expect, content);
  }

  @Test
  public void testGetFileFromClassPath() throws URISyntaxException {
    Assert.assertNotNull(RFiles.getFileFromClassPath("/file_utf_8.txt"));
  }

  @Test
  public void testGetFileFromClassPath2() throws URISyntaxException {
    Assert.assertNull(RFiles.getFileFromClassPath("file_utf_8.txt"));
  }

  @Test
  public void testToStringCharset() throws Exception {
    String content = RFiles.toString(RFiles.getFileFromClassPath("/file_gb2312.txt"));
    String expect = "测试读取文件1\n"
        + "测试读取文件2\n"
        + "测试读取文件3\n"
        + "测试读取文件4\n"
        + "测试读取文件5\n";
    Assert.assertNotEquals(expect, content);
    content = RFiles
        .toString(RFiles.getFileFromClassPath("/file_gb2312.txt"), Charset.forName("gb2312"));
    Assert.assertEquals(expect, content);
  }

}