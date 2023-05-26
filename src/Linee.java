import java.util.ArrayList;
public class Linee {
    int src; //Source of line
    int srcNo; //Number of sources
    int dst; //Destination of the line
    int dstNo; //Number of Destinations
    int zOrder; //IDK
    ArrayList<Branch> branches=new ArrayList<>();
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
    public void printBranches(){
        System.out.printf("Branches No:%d:\n",branches.size());
        if(branches.size()>0){
        for(int i=0;i<branches.size();i++){
            branches.get(i).printBranch();
        }
        }
    }
}
