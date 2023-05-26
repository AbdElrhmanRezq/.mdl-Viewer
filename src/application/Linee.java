package application;

import java.util.ArrayList;

import javafx.geometry.Point2D;
public class Linee {
    int src; //Source of line
    int srcNo; //Number of sources
    int dst; //Destination of the line
    int dstNo; //Number of Destinations
    int zOrder; //IDK
    int[] points = new int[4];
    ArrayList<Branch> branches=new ArrayList<>();
    Point2D startPoint;
    Point2D endPoint;
    boolean rotate;
    boolean tri;
    
    Linee(){}
    Linee(int src,int srcNo,int dst,int dstNo,int zOrder){
        this.src=src;
        this.srcNo=srcNo;
        this.dst=dst;
        this.dstNo=dstNo;
        this.zOrder=zOrder;
    }

    //Getters
    int getSrc(){
        return src;
    }
    int getSrcNo(){
        return srcNo;
    }
    int getdst(){
        return dst;
    }
    int getDstNo(){
        return dstNo;
    }
    int getZOrder(){
        return zOrder;
    }
    Branch getBranch(int pos){
        return branches.get(pos);
    }

    //Setters
    void setSrc(int src){
        this.src=src;
    }
    void setSrcNo(int srcNo){
        this.srcNo=srcNo;
    }
    void setDst(int dst){
        this.dst=dst;
    }
    void setDstNo(int dstNo){
        this.dstNo=dstNo;
    }
    void setZOrder(int zOrder){
        this.zOrder=zOrder;
    }
    void setBranch(int pos, Branch value){
        branches.set(pos,value);
    }
    void addBranch(Branch branch){
        branches.add(branch);
    }
    void addBranches(ArrayList<Branch> branches){
        this.branches=branches;
    }
    void printLine(){
        System.out.printf("Src: %d\nSrcNo: %d\nDst: %d\nDstNo: %d\nzOrder: %d\n",src,srcNo,dst,dstNo,zOrder);
        System.out.printf("Branches: %d\n",branches.size());
    }
    
    void printPoints(){
        System.out.printf("(%f, %f) , (%f, %f)\n", startPoint.getX(), startPoint.getY(),
        		endPoint.getX(),endPoint.getY());
    }
    
    public void printBranches(){
        System.out.printf("Branches No:%d:\n",branches.size());
        if(branches.size()>0){
        for(int i=0;i<branches.size();i++){
            branches.get(i).printBranch();
        }
        }
    }
	public int[] getPoints() {
		return points;
	}
	public void setPoints(int[] points) {
		this.points = points;
	}
	
	void setPoints(String poString){
        String temp="";
        int it=0;
        for(int i=1;i<poString.length();i++){
            if(poString.charAt(i)!=',' && poString.charAt(i)!=' ' && poString.charAt(i)!=']' && poString.charAt(i)!=';'){
                temp+=poString.charAt(i);
            }
            else if (poString.charAt(i)==',' || poString.charAt(i)==';' || poString.charAt(i)==']'){
                points[it]=Integer.valueOf(temp);
                //System.out.println(temp);
                temp="";
                it++;
            }
        }
    }
	
	public void printPoint(){
        System.out.printf("(%d,%d) ; (%d,%d)\n",points[0],points[1],points[2],points[3]);
}
}

