package cc.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class CommonController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response) throws IOException {
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf('_') + 1);
        String filePath = "file/" + fileName;
        File file = new File(filePath);
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(realFileName, "utf-8"));
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            log.error("文件下载失败", e);
        } finally {
            if (delete && file.exists()) {
                Files.delete(Paths.get(filePath));
            }
        }
    }
}
