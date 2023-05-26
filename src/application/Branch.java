package application;

public class Branch {
    int dst; //Destination of the line
    int dstNo; //Number of Destinations
    int zOrder; //IDK
    int[] points=new int[2];
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
    void printBranch(){
        System.out.printf("Dst:%d\nDstNo:%d\nzOrder:%d\n",dst,dstNo,zOrder);
    }
    public void printPoints(){
        System.out.printf("(%d,%d)\n",points[0],points[1]);
}
}