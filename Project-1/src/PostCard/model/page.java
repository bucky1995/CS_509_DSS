package PostCard.model;

import java.awt.*;
import java.util.ArrayList;

public class page {
    ArrayList<String> visualE;
    public page(){
        visualE = new ArrayList<String>();
    }

    void addElement(String config){
        visualE.add(config);
    }

    public void setE(ArrayList<String> es){
        visualE = es;
    }

    public ArrayList<String> getE(){
        return visualE;
    }
}
