package PostCard.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class card {
    public String type;
    public int orientation;
    public String recipient;

    private String[] orientationList = {
            "landscape : 900 x 600",
            "portrait : 600 x 900"
    };
    public card(int ori, String EType,String re){
        this.type = EType;
        this.orientation = ori;
        this.recipient = re;
    }

    public boolean save()throws IOException{
        File archiveDir = new File("archive");
        if(!archiveDir.exists()){
            archiveDir.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String fileName = this.type+"-"+ this.recipient+"-"+ currentTime;
        File file = new File(archiveDir,fileName);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(type+"\n");
        fw.write(recipient+"\n");
        fw.write(orientation+"\n");
        fw.write(currentTime.toString()+"\n");
        fw.flush();
        fw.close();
        return true;
    }


}
