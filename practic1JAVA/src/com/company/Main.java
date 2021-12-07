package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;

import com.company.Txt;
import java.util.Scanner;
import com.company.JSONcl;
import com.company.XMLcl;
import com.company.ZIPcl;
import org.xml.sax.SAXException;
public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println("Root: " + roots[i]);
        }

        System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("File system roots returned by File.listRoots():");
        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++) {
            System.out.println("Drive: " + f[i]);
            System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
            System.out.println("Is drive: " + fsv.isDrive(f[i]));
            System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
            System.out.println("Readable: " + f[i].canRead());
            System.out.println("Writable: " + f[i].canWrite());
            System.out.println("Total space: " + f[i].getTotalSpace());
            System.out.println("Usable space: " + f[i].getUsableSpace());
        }
//_________________________________________________txt
//        System.out.println("txt___________________________________");
//        Txt txt = new Txt();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите название файла");
//        String tempname=sc.next();
//        txt.Create_txt("C:\\Users\\madca\\Desktop\\"+tempname+".txt");
//        System.out.println("Введите текст");
//        String temp=sc.next();
//        txt.WriteToTxt(temp);
//        System.out.println("Содержимое файла");
//        txt.ReadFromTxt();
        //txt.Delete_txt();
        System.out.println("Json___________________________________");
        JSONcl js=new JSONcl();
        js.Create_json("C:\\Users\\madca\\Desktop\\new.json");
        js.WriteToJSON("info1","info2","info3");
        System.out.println("Содержимое файла");
        js.ReadFromJSON();
//        js.DeleteJSON();
 //________________________________________________XML
        System.out.println("Xml___________________________________");
        XMLcl xml=new XMLcl();
        xml.WriteToXML("C:\\Users\\madca\\Desktop\\new.xml");
        System.out.println("Содержимое файла");
        xml.ReadFromXML();

// ________________________________________________ZIP
        System.out.println("Zip___________________________________");
          ZIPcl zp=new ZIPcl();
          zp.CreateZip("C:\\Users\\madca\\Desktop\\ZIPcl.zip");
          zp.AddToZip();
          zp.ReadFromZip();
    }

}
