package backend.mybatis.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class UseMsedge {
    static String downloadLocation = "D:\\";
    public static void openBrowser (String url) throws Exception {
        // 浏览器要打开的链接URL
//        String url = "https://covid19.who.int/who-data/vaccination-metadata.csv";
        // 获取操作系统的名字
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac OS")) {
            // 操作系统为 Mac （苹果电脑）
            Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
            openURL.invoke(null, new Object[] { url });
        } else if (osName.startsWith("Windows")) {
            // 操作系统为 Windows
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else {
            // 操作系统为 Linux 或 Unix
            String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++) {
                // 执行代码，在brower有值后跳出，
                // 这里是如果进程创建成功了，==0是表示正常结束。
                if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
                    browser = browsers[count];
                }
            }
            if (browser == null) {
                throw new Exception("Could not find web browser");
            } else {
                // 这个值在上面已经成功的得到了一个进程。
                Runtime.getRuntime().exec(new String[] { browser, url });
            }
        }
        Thread.sleep(10 * 1000); //等待10秒确保下载完成
    }
    public static void closeBrowse(){
        try {
//            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDownloadLocation(String downloadLocation) {
        UseMsedge.downloadLocation = downloadLocation;
    }

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public static void moveFile(String from,String to){

        File file=new File(from);
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(file);
            fos=new FileOutputStream(new File(to));

            byte[] bs=new byte[50];
            int count=0;
            while((count=fis.read(bs))!=-1){
                fos.write(bs, 0, count);
                fos.flush();
            }
            fis.close();
            fos.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFile(String path){
        File f = new File(path);
        f.delete();
    }
}
