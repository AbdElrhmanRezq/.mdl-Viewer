public class Branch {
    int dst; //Destination of the line
    int dstNo; //Number of Destinations
    int zOrder; //IDK
    Branch(){}
    Branch(int dst,int dstNo,int zOrder){
        this.dst=dst;
        this.dstNo=dstNo;
        this.zOrder=zOrder;
    }

    //Getters
    int getdst(){
        return dst;
    }
    int getDstNo(){
        return dstNo;
    }
    int getZOrder(){
        return zOrder;
    }

    //Setters
    void setDst(int dst){
        this.dst=dst;
    }
    void setDstNo(int dstNo){
        this.dstNo=dstNo;
    }
    void setZOrder(int zOrder){
        this.zOrder=zOrder;
    }
    void printBranch(){
        System.out.printf("Dst:%d\nDstNo:%d\nzOrder:%d\n",dst,dstNo,zOrder);
    }
}
