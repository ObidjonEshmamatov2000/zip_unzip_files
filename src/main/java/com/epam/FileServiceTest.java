package com.epam;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileServiceTest {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        String folderURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project\\zip_files";
        String zippingFileName = "multiZep.zip";
        File zippedFile = fileService.zip(folderURL, zippingFileName);
        System.out.println(zippedFile.getName());

        String zipFileURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project\\zip_unzip_project\\multiZep.zip";
        File zipFile = new File(zipFileURL);
        String destinationURL = "C:\\Users\\Obidjon_Eshmamatov\\Desktop\\java projects\\zip_unzip_project\\zip_unzip_project";
        String result = fileService.unzip(zipFile, destinationURL);
        System.out.println(result);

        List<File> fileList = fileService.unzip(zipFile);
        System.out.println(fileList.size());

    }
}
