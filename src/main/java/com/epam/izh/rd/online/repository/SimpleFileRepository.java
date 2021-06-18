package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.*;

public class SimpleFileRepository implements FileRepository {


    @Override
    public long countFilesInDirectory(String path) {
        int count = 0;
        File folder = new File("src/main/resources/" + path);
        File[] folders = folder.listFiles();
        for (File file : folders) {
            if (file.isFile()) {
                count++;
            }
            if (file.isDirectory()) {
                count += countFilesInDirectory(path + "/" + file.getName());
            }
        }
        return count;
    }


    @Override
    public long countDirsInDirectory(String path) {
        File folder = new File("src/main/resources/" + path);
        File[] listFolder = folder.listFiles();
        long count = 1;
        if (listFolder != null) {
            for (File file : listFolder) {
                if (file.isDirectory()) {
                    count += countDirsInDirectory(path + "/" + file.getName());
                }
            }
        }
        return count;
    }


    @Override
    public void copyTXTFiles(String from, String to) {
        File source = new File(from).getParentFile();
        File destination = new File(to).getParentFile();
        File[] folder = source.listFiles();
        if (!destination.exists()) {
            destination.mkdirs();
        }
        for (File file : folder) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), new File(destination + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean createFile(String path, String name) {
        File folder = new File(getClass().getResource("/").getPath() + path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder.getPath() + "/" + name);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder textBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }
}
