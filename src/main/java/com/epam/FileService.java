package com.epam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileService {

    public List<File> unzip(File zipFile) throws IOException{
        String destinationURL = System.getProperty("user.dir");
        unzip(zipFile, destinationURL);
        String zipFileName = zipFile.getName();
        return getFilesList(destinationURL + "\\" + zipFileName.substring(0, zipFileName.length() - 4));
    }

    public String unzip(File zipFile, String destinationURL) throws IOException {
        String fileName = zipFile.getName();
        File destDir = new File(destinationURL, fileName.substring(0, fileName.length() - 4));
        byte[] bytes = new byte[1024];
        FileInputStream fis = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(bytes)) > 0) {
                    fos.write(bytes, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return "successfully zipped to the given destination";
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public File zip(List<File> files, String zipFileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (File fileToZip: files) {
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
        return new File(zipFileName);
    }

    public File zip(String folderURL, String zipFileName) throws IOException {
        List<File> files = getFilesList(folderURL);
        return zip(files, zipFileName);
    }

    private List<File> getFilesList(String folderURL) {
        List<File> files = new ArrayList<File>();
        File dir = new File(folderURL);
        File[] filesArray = dir.listFiles();
        for (File file: filesArray) {
            if (file.isFile()) files.add(file);
        }
        return files;
    }
}
