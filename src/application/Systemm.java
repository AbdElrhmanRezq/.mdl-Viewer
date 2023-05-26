package application;


import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Systemm{
    ArrayList<Linee> lines=new ArrayList<>();
    ArrayList<Block> blocks=new ArrayList<>();
    ArrayList<Linee> branchestoLines = new ArrayList<>();
    ArrayList<Polygon> triangles = new ArrayList<>();
    
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
    
    
    
    void getStartPoint() {
    	for(Linee line : lines) {
			for (Block block : blocks) {
				if(Integer.valueOf(block.SID)  == line.src)
				{
					 if(block.getName().contains("Unit Delay"))
						 line.startPoint = new Point2D(block.position[0]
									, block.getCenter().getY() - 5);       
					 else
						 line.startPoint = new Point2D(block.position[2]
								 , block.getCenter().getY());       
					
				}
			}
			if(line.branches.size() > 0)
			{
				for (Branch branch : line.branches) {
					Linee newLine = new Linee();
					newLine.src = line.src;
					newLine.dst = branch.dst;
					newLine.startPoint = new Point2D(line.startPoint.getX() + line.getPoints()[0]
							, line.startPoint.getY());
					newLine.points[0] = branch.points[0];
					newLine.points[1] = branch.points[1];
					
					branchestoLines.add(newLine);
				}
			}
		}
    }
    
    void getEndPoint() {
    	Linee newLine = new Linee();
    	Linee newLine2 = new Linee();
    	for(Linee line : lines) {
    		if(line.branches.size() > 0)
    			line.setDst(line.branches.get(1).dst);
			for (Block block : blocks) {
				
				if(Integer.valueOf(block.SID)  == line.dst)
				{
					if( (block.position[1] < (line.startPoint.getY() - 25)))
					{
						line.endPoint = new Point2D(line.startPoint.getX() + line.points[0] 
								, line.startPoint.getY());
						line.tri = true;
						
						newLine.startPoint = line.endPoint;
						newLine.endPoint = new Point2D(line.endPoint.getX(),
								block.position[3] - 6);
						newLine.tri = true;
						
						newLine2.startPoint = newLine.endPoint;
						newLine2.endPoint = new Point2D(block.position[0],
								newLine.endPoint.getY());
					}
					else
						line.endPoint = new Point2D(block.position[0] 
								, line.startPoint.getY());
				}
			}
		}
    	lines.add(newLine);
    	lines.add(newLine2);
    	for(Linee line : branchestoLines) {
			for (Block block : blocks) {
				
				if(Integer.valueOf(block.SID)  == line.dst)
				{
					line.endPoint = new Point2D(line.startPoint.getX()
							, line.startPoint.getY() + line.points[1]);
					if(line.points[1] != 0)
					{
						Linee newLine1 = new Linee();
						newLine1.startPoint = line.endPoint;
						
						if(line.endPoint.getX() > block.position[2])
						{
							newLine1.endPoint = new Point2D(block.position[2] + 3, newLine1.startPoint.getY());
							newLine1.rotate = true;
						}
						else
							newLine1.endPoint = new Point2D(block.position[0], newLine1.startPoint.getY());
						
						lines.add(newLine1);
					}
					
				}
			}
		}
    }
    
    void makeTriangles() {
    	for(Linee line : lines) {
    		if(line.tri)
    			continue;
    			
    		
    		Polygon tri = new Polygon();
    		tri.setFill(Color.BLACK);
            tri.setStroke(Color.BLUE);   
            
            
            tri.getPoints().addAll(line.endPoint.getX(), line.endPoint.getY(),
            		line.endPoint.getX() - 8, line.endPoint.getY() + 6,
            		line.endPoint.getX() - 8, line.endPoint.getY() - 6);
            
            if(line.rotate)
            	tri.setRotate(180);
            
            triangles.add(tri);
        
    	}

    	
    }
    
    	

}