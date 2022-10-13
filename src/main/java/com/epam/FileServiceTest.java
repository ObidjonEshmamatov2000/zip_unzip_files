package com.epam;

import java.io.File;
import java.io.IOException;

public class FileServiceTest {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        String folderURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project\\zip_files";
        String zippingFileName = "multiZep.zip";
        File zippedFile = fileService.zip(folderURL, zippingFileName);
        System.out.println(zippedFile.getName());

//        String zippedFileURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project\\zip_unzip_project\\multiZep.zip";
//        String destinationURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project";
//        List<File> fileList = fileService.unzip(new File(zippedFileURL));
//        System.out.println(fileList);
    }
}
