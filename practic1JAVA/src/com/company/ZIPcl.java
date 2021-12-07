package com.company;
import java.io.IOException;
import java.util.zip.*;
import java.io.*;
import javax.swing.*;
public class ZIPcl {
    private  static ZipOutputStream zout;
    private String absolute_path;
    private String temp_path;
    public void CreateZip(String Zname)throws IOException {
        zout = new ZipOutputStream(new FileOutputStream(Zname));
        absolute_path=Zname;
        zout.close();

    }
    public void AddToZip()throws IOException{
        zout = new ZipOutputStream(new FileOutputStream(absolute_path));
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ZipEntry entry1=new ZipEntry(file.getName());
            FileInputStream fis= new FileInputStream(file);
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            zout.close();
        }
    }

    public void ReadFromZip()throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Users\\madca\\Desktop\\ZIPcl.zip"));
        ZipEntry entry;
        String name;
        long size;
        while ((entry = zin.getNextEntry()) != null) {
            name = entry.getName();
            size = entry.getSize();
            System.out.printf("file name: %s \t file size: %d \n", name, size);
            temp_path = "C:\\Users\\madca\\Desktop\\" + name;
            FileOutputStream fout = new FileOutputStream("C:\\Users\\madca\\Desktop\\" + name);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();

        }
        zin.close();
    }

    public void DeleteZIP(){
        File temp=new File(absolute_path);
        temp.delete();
        temp=new File(temp_path);
        temp.delete();
    }




}
