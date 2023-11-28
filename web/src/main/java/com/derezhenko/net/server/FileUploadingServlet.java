package com.derezhenko.net.server;

import com.cloudinary.Cloudinary;
import com.derezhenko.net.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;




@MultipartConfig(fileSizeThreshold = 1024 * 1024, //Порог размера, после которого файл будет записан на диск
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5, //Максимальный размер, разрешенный для multipart/form-data Запросы
        location = "/test-upload" // Загружает вот сюда
)
@WebServlet(urlPatterns = "/upload")
public class FileUploadingServlet extends HttpServlet {

    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    public static final String FILE_PATH_PREFIX = "/tmp";
    public static final int DIRECTIONS_COUNT = 100;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Upload - doPost");
        Part part = req.getPart("upload-file");
        System.out.println("after part");

        String filename = Paths.get(part.getSubmittedFileName()).toString();

        File file = new File(FILE_PATH_PREFIX + File.separator + filename.hashCode() % DIRECTIONS_COUNT + File.separator + filename);

        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];

        content.read(buffer);

        fos.write(buffer);
        fos.close();

        cloudinary.uploader().upload(file, new HashMap<>());

    }
}
