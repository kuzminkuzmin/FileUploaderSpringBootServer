package com.dmitriikuzmin.service;

import com.dmitriikuzmin.model.UserFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface UserFileService {
    boolean checkDir(String dir);

    boolean createDir(String dir);

    boolean deleteDir(String dir);

    UserFile add(long userId, MultipartFile file, String dir);

    //void downloadFile(HttpServletResponse response, UserFile userFile);

    UserFile get(long id);
}
