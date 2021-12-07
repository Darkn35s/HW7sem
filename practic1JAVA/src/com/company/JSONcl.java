package com.company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.*;
public class JSONcl {
    private static final Gson JSON=new GsonBuilder().setPrettyPrinting().create();
    private File json_file;
    private String absolute_path;
    public void Create_json(String absolute_path) throws IOException {
        this.absolute_path = absolute_path;
        json_file = new File(absolute_path);
        if (json_file.createNewFile()) {
            System.out.println("File successfully created:" + absolute_path);
        }
    }

    public void WriteToJSON(String data1,String data2, String data3) throws  IOException{
        DataFrame df=new DataFrame(data1,data2,data3);
        String serelized=JSON.toJson(df);
        FileWriter tmp_file=new FileWriter(absolute_path,false);
        tmp_file.write(serelized);
        tmp_file.close();
    }

    public void ReadFromJSON() throws IOException{
        try(FileReader reader = new FileReader(absolute_path))
        {
          DataFrame temp=JSON.fromJson(reader,DataFrame.class);
          System.out.println(temp.data1+", "+temp.data2+", "+temp.data3);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }


    }
    public void DeleteJSON(){
        json_file.delete();
    }

}
class DataFrame{
    String data1;
    String data2;
    String data3;
    public DataFrame(String data1,String data2, String data3){
        this.data1=data1;
        this.data2=data2;
        this.data3=data3;
    }
}


