
import cn.hutool.core.io.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
//
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;


public class test {
    public static void main(String[] args) throws IOException {

//        BufferedWriter writeText = new BufferedWriter(new FileWriter(path));
//
//        for(int i=1;i<=10;i++){
//            writeText.newLine();    //换行
//            //调用write的方法将字符串写到流中
//            writeText.write("新用户"+i+",男,"+(18+i));
//        }
//
//        //使用缓冲区的刷新方法将数据刷到目的地中
//        writeText.flush();
//        //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
//        //因此，此处的close()方法关闭的是被缓存的流对象
//        writeText.close();
//        System.out.println("asdasd".compareTo("sdasd"));
        String path = "src/main/java/backend/tables/test.csv";
        URL url = new URL("https://covid19.who.int/WHO-COVID-19-global-data.csv");
        InputStream inputStream = url.openStream();
        File f = new File(path);
        OutputStream outputStream = new FileOutputStream(f);



        //1M逐个读取
        int byteCount = 0;
        byte[] bytes = new byte[1024*1024*2000];
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        inputStream.close();
        outputStream.close();
    }

//    @GetMapping("/download")
//    public void download(HttpServletResponse response, @RequestParam String path) throws Exception {
//
//        // 让servlet用UTF-8转码，默认为ISO8859
//        response.setCharacterEncoding("UTF-8");
//
//        File file = new File(path);
//        if (!file.exists()) {
//            // 让浏览器用UTF-8解析数据
//            response.setHeader("Content-type", "text/html;charset=UTF-8");
//            response.getWriter().write("文件不存在或已过期,请重新生成");
//            return;
//        }
//
//        String fileName = URLEncoder.encode(path.substring(path.lastIndexOf("/") + 1), "UTF-8");
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
//        InputStream is = null;
//        OutputStream os = null;
//
//        try {
//            is = new FileInputStream(path);
//            byte[] buffer = new byte[1024];
//            os = response.getOutputStream();
//            int len;
//            while((len = is.read(buffer)) > 0) {
//                os.write(buffer,0, len);
//            }
//        }catch(Exception e) {
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                if (is != null) is.close();
//                if (os != null) os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}



//}
