public class Block {
    String blockType; //Type of block
    String name; //Name of block
    String SID; //ID
    int zOrder; 
    String posString;
    int[] position=new int[4]; //Position [Left,Top,Right,Bottom]
    Block(){}
    Block(String blockType, String name, String SID, int zOrder, int position[]){
        this.blockType=blockType;
        this.name=name;
        this.SID=SID;
        this.zOrder=zOrder;
        this.position=position;
    }

    //Getters
    String getType(){
        return blockType;
    }
    String getName(){
        return name;
    }
    String getSID(){
        return SID;
    }
    int getZOrder(){
        return zOrder;
    }
    int[] getPosition(){
        return position;
    }

    //Setters
    void setType(String type){
        this.blockType=type;
    }
    void setName(String name){
        this.name=name;
    }
    void setSID(String SID){
        this.SID=SID;
    }
    void setZOrder(int zOrder){
        this.zOrder=zOrder;
    }
    void setPosition(int[] pos){
        this.position=pos;
    }
    void setPosString(String pos){
        this.posString=pos;
    }

    void printBlock(){
        System.out.printf("%s\n%s\n%s\n%d\n%s\n",name,blockType,SID,zOrder,posString);
    }
    
}
