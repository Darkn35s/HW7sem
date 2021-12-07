package com.company;
import java.io.*;

public class Txt {
    private File txt_file;
    private String absolute_path;
    public void Create_txt(String absolute_path) throws IOException {
        this.absolute_path=absolute_path;
        txt_file=new File(absolute_path);
        if(txt_file.createNewFile()) {
            System.out.println("File successfully created:"+absolute_path);
        }

    }
    public void WriteToTxt(String message) throws IOException {
        FileWriter tmp_file=new FileWriter(absolute_path,true);
        tmp_file.write(message+"\n");
        tmp_file.close();
    }

    public void ReadFromTxt() throws IOException{
        try(FileReader reader = new FileReader(absolute_path))
        {
            int c;
            while((c=reader.read())!=-1){
                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void Delete_txt(){
        txt_file.delete();
        System.out.println("Файл успешно удален");

    }
}
