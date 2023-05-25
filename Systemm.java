import java.util.ArrayList;
public class Systemm{
    ArrayList<Linee> lines=new ArrayList<>();
    ArrayList<Block> blocks=new ArrayList<>();
    Systemm(){}
    Systemm(Block block){
        blocks.add(block);
    }

    //Methods to add
    void addBlock(Block block){
        blocks.add(block);
    }
    void addLine(Linee line,ArrayList<Branch> branches){
        line.addBranches(branches);
        lines.add(line);
    }

    //Methods to get
    Block getBlock(int pos){
        return blocks.get(pos);
    }
    Linee getLine(int pos){
        return lines.get(pos);
    }

    //Setters
    void setBlock(int pos,Block value){
        blocks.set(pos, value);
    }
    void setLine(int pos,Linee value){
        lines.set(pos, value);
    }
    
}