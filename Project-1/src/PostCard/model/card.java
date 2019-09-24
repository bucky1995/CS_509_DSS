package PostCard.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.security.krb5.internal.crypto.EType;

public class card {
    public String type;
    public int orientation;
    public String recipient;
    public page front;
    public page i_left;
    public page i_right;
    public page back;

    private String[] orientationList = {
            "landscape : 900 x 600",
            "portrait : 600 x 900"
    };

    public card(int ori, String EType,String re){
        this.type = EType;
        this.orientation = ori;
        this.recipient = re;
        front = new page();
        i_left = new page();
        i_right = new page();
        back = new page();
    }

    public void save() throws IOException, JSONException {
        File archiveDir = new File("archive");
        if(!archiveDir.exists()){
            archiveDir.mkdir();
        }
        String fileName = this.type+"-"+ this.recipient+".json";
        File file = new File(archiveDir,fileName);
        file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        JSONObject cjson = new JSONObject();
        cjson.put("type",this.type);
        cjson.put("orientation",this.orientation);
        cjson.put("recipient",recipient);

        ArrayList<String> temp;
        temp = front.getE();
        JSONArray f = new JSONArray(temp);
        JSONObject fo = new JSONObject();
        fo.put("front",f);
        //temp.clear();
        temp = i_left.getE();
        JSONArray il = new JSONArray(temp);
        fo.put("inner_left",il);
        //temp.clear();
        temp = i_right.getE();
        JSONArray ir = new JSONArray(temp);
        fo.put("inner_right",ir);
        //temp.clear();
        temp = back.getE();
        JSONArray b = new JSONArray(temp);
        fo.put("back",b);
        cjson.put("visual element",fo);
        cjson.write(fw);
        fw.flush();
        fw.close();
    }

    public void read(String fileName) throws IOException, JSONException {
        String filePath = "archive/"+fileName;
        File f = new File(filePath);
        JSONParser jsonParser = new JSONParser();
        org.json.simple.JSONObject cjson = null;
        try{
            Object obj = jsonParser.parse(new FileReader(filePath));
            cjson = (org.json.simple.JSONObject)  obj;
        } catch (ParseException | IOException ex){
            ex.printStackTrace();
        }
        orientation = Integer.parseInt(cjson.get("orientation").toString());
        type = cjson.get("type").toString();
        recipient = cjson.get("recipient").toString();
        ArrayList<String > temp = new ArrayList<String>();
        org.json.simple.JSONObject VE = null;
        VE = (org.json.simple.JSONObject) cjson.get("visual element");
        String[] key = {"front","inner_left","inner_right","back"};
        for(int i=0;i<key.length;i++){
            temp = (ArrayList<String>) VE.get(key[i]);
            setVE(i,temp);
        }
    }

    public boolean addVE(int page,String e) throws IOException, JSONException {
        switch (page){
            case 0: front.addElement(e);break;
            case 1: i_left.addElement(e);break;
            case 2: i_right.addElement(e);break;
            case 3: back.addElement(e);break;
        }
        save();
        return true;
    }

    public void deleteVE(int page, String e) throws IOException, JSONException {
        if(page == 3){
            return;
        }
        page p;
        switch (page){
            case 0:p = front;
            break;
            case 1:p = i_left;
            break;
            case 2:p = i_right;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + page);
        }
        ArrayList<String> vEs =p.getE();
        for(int i=0;i<vEs.size();i++){
            if(vEs.get(i).equals(e)){
                vEs.remove(i);
                break;
            }
        }
        switch (page){
            case 0:front.setE(vEs);
                break;
            case 1:i_left.setE(vEs);
                break;
            case 2:i_right.setE(vEs);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + page);
        }
        save();
    }

    private void setVE(int page, ArrayList<String> es) throws IOException, JSONException {
        switch (page){
            case 0: front.setE(es);break;
            case 1: i_left.setE(es);break;
            case 2: i_right.setE(es);break;
            default:break;
        }
    }



}
