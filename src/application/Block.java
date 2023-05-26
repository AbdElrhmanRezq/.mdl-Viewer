package application;

import javafx.geometry.Point2D;

public class Block {
    String blockType; //Type of block
    String name; //Name of block
    String SID; //ID
    int zOrder; 
    String posString;
    int[] position=new int[4]; //Position [Left,Top,Right,Bottom]
    
    private Point2D center;
    private double length = 50;
    private double width = 50;
    
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
        String temp="";
        int it=0;
        for(int i=1;i<pos.length();i++){
            if(pos.charAt(i)!=',' && pos.charAt(i)!=' ' && pos.charAt(i)!=']'){
                temp+=pos.charAt(i);
            }
            else if (pos.charAt(i)!=','){
                position[it]=Integer.parseInt(temp);
                temp="";
                it++;
            }
        }
        int x = (position[0] + position[2]) / 2;
        int y = (position[1] + position[3]) / 2;
       
        //setLength((position[2] - position[0]) * 1.5); //Left to Right
        //setWidth((position[3] - position[1]) * 1.5);	//Top to bottom
        setCenter(new Point2D(x,y));
    }
    
    void printBlock(){
        System.out.printf("%s\n%s\n%s\n%d\n%s\n",name,blockType,SID,zOrder,posString);
    }
	public Point2D getCenter() {
		return center;
	}
	public void setCenter(Point2D center) {
		this.center = center;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
    
}
