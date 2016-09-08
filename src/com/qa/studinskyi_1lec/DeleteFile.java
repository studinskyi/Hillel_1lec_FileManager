package com.qa.studinskyi_1lec;

import java.io.File;

public class DeleteFile extends FileManager {
    @Override
    public String getName() {
        // "rm - delete file"
        return "delete file (rm \"name file\")";
    }

    @Override
    public void execute() {
        String fullPathToFile = "";
        String nameFile = "";

        // проверка наличия введенного параметра имени файла
        if (FileManager.commandParameters.size() > 0) {
            try {
                nameFile = FileManager.commandParameters.get(1);
            } catch (Exception e) {
                nameFile = "";
            }
        }

        while (true) {
            if (nameFile.equals("")) {
                String answer = FileManager.requestLine("Delete file? y\\n");
                if (!answer.equals("y")) {
                    System.out.println("file deletion is canceled");
                    break;
                }
                nameFile = FileManager.requestLine("enter the name of the file to delete:");
            }

            if (!nameFile.equals("")) {
                fullPathToFile = folderFile + nameFile + ".txt"; // + File.separator
                if (fileExist(fullPathToFile)) {
                    //if (f.exists()) {
                    System.out.println("file is deleted: " + fullPathToFile);
                    File f = new File(fullPathToFile);
                    f.delete();

                    if (fileExist(fullPathToFile)) {
                        try {
                            throw new ExceptionFileNotDeleted("File has not been deleted:" + fullPathToFile);
                        } catch (ExceptionFileNotDeleted e) {
                            e.printStackTrace();
                        }
                    }

                } else
                    System.out.println("file does not exist, it can not be deleted: " + fullPathToFile);
            }
            nameFile = "";
        }

        //        File dir = new File(folderFile);
        //        File fileForDelet = null;
        //        if (dir.isDirectory()) {
        //            // если объект представляет каталог
        //            for (File item : dir.listFiles()) {
        //                // получаем все вложенные объекты в каталоге
        //                if (!item.isDirectory()) {
        //                    fileForDelet = item;
        //                    break;
        //                }
        //            }
        //        }
    }
}