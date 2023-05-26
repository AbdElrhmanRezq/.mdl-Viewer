package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    
    
    public static Systemm read(String args){
    	 Systemm system=new Systemm(); //Create an object of the system class
    	try{
            String fileName=args; //Take the name of the .mdl file -> Given by terminal
            if(!fileName.endsWith(".mdl")){
                throw new NotValidSimulinkFile("not MDL file");
            }

            File file=new File(fileName);
            if(file.length()==0){
                throw new EmptyFile("File is Empty");
            }
            
            FileInputStream fileStream=new FileInputStream(file); //To input characters to system
            int charByte;
            StringBuilder stringBuilder=new StringBuilder(); //Build a string of characters
            while((charByte=fileStream.read())!=-1){
                stringBuilder.append((char)charByte);
            }
            //System.out.println(stringBuilder);
            
            String example=stringBuilder.toString(); //Convert srtring builder to string
            //System.out.println(example);
            Scanner scanner=new Scanner(example);
           
            
            //The following varaiables are declared because of a problem that faced me
            //Tyring to use while loops and scanner.nextLine() -> It didn't work for some reason
            Boolean sys=false; //Flag for <System> part
            Boolean bl=false;  //Flag for <Block> part
            Boolean ln=false;  //Flag for <Line> part
            Boolean br=false;  //Flag for <Branch> part
            Block block=new Block(); //Stores data temporary
            Linee linee=new Linee(); //Stores data temporary
            Branch branch=new Branch(); //Stores data temporary
            ArrayList<Branch> branches=new ArrayList<>(); //Stores Array of Branches temporary
            while(scanner.hasNextLine()){

                String line=scanner.nextLine(); //Go to the next line
                //System.out.println(line);
                if(line.contains("<System>")) sys=true; 
                else if(line.contains("</System")) {sys=false;}
                if(sys==true){ //Entered <System>
                    if(line.contains("<Block")) {bl=true;}
                    else if(line.contains("</Block") && (bl==true)) {bl=false; system.addBlock(block);block =new Block();} //If <Block> block ended -> Add block to the system
                    if(line.contains("<Line")) {ln=true;}
                    else if(line.contains("</Line") &&(ln==true)) {ln=false;system.addLine(linee,branches);linee=new Linee();branches=new ArrayList<>();} //If <Line> line ended -> Add line to the system
                    if(line.contains("<Branch")) br=true;
                    else if(line.contains("</Branch")) {
                    br=false; 
                    branches.add(branch); //Add branch to the temporary branches which will be added to the corresponding line
                    branch=new Branch(); 
                    }

                    if(bl==true){ //Next Lines just take the lines as strings take the important data in it and turn it to integer if needed
                        if(line.contains("<Block")){
                            block.setName(line.substring(line.indexOf("Name=")+6, line.indexOf("\" SID")));
                            block.setType(line.substring(line.indexOf("BlockType=")+11, line.indexOf("\" Name")));
                            block.setSID(line.substring(line.indexOf("SID=")+5, line.indexOf("\">")));

                        }
                        if(line.contains("Name=\"Position\"")){
                            block.setPosString(line.substring(line.indexOf("\"Position\">")+11, line.indexOf("</P")));
                        }
                        else if(line.contains("Name=\"ZOrder\"")){
                            String temp=line.substring(line.indexOf("\"ZOrder\">")+9, line.indexOf("</P")); //A Temporary string holding zOrder string
                            block.setZOrder(Integer.parseInt(temp)); //Set to the integer value of temp 
                        }
                    }
                    else if(ln==true){
                        if(br==true){
                            if(line.contains("Name=\"ZOrder\"")){
                                String temp=line.substring(line.indexOf("\"ZOrder\">")+9, line.indexOf("</P")); //A Temporary string holding zOrder string
                                branch.setZOrder(Integer.parseInt(temp)); //Set to the integer value of temp 
                            }
                            if(line.contains("Name=\"Dst\"")){
                                String temp=line.substring(line.indexOf("\"Dst\">")+6, line.indexOf("</P"));
                                String firstNo=temp.substring(0, temp.indexOf("#"));
                                String secondNo=temp.substring(temp.indexOf(":")+1);
                                branch.setDst(Integer.parseInt(firstNo));
                                branch.setDstNo(Integer.parseInt(secondNo));

                            }else if(line.contains("Name=\"Points\"")){
                                String temp=line.substring(line.indexOf("\"Points\">")+9, line.indexOf("</P"));
                                branch.setPoints(temp);
                            }
                        }
                        else if(br==false){
                            if(line.contains("Name=\"ZOrder\"")){
                                String temp=line.substring(line.indexOf("\"ZOrder\">")+9, line.indexOf("</P")); //A Temporary string holding zOrder string
                                linee.setZOrder(Integer.parseInt(temp)); //Set to the integer value of temp 
                            }
                            else if(line.contains("Name=\"Dst\"")){
                                String temp=line.substring(line.indexOf("\"Dst\">")+6, line.indexOf("</P"));
                                String firstNo=temp.substring(0, temp.indexOf("#"));
                                String secondNo=temp.substring(temp.indexOf(":")+1);
                                linee.setDst(Integer.parseInt(firstNo));
                                linee.setDstNo(Integer.parseInt(secondNo));

                            }
                            else if(line.contains("Name=\"Src\"")){
                                String temp=line.substring(line.indexOf("\"Src\">")+6, line.indexOf("</P"));
                                String firstNo=temp.substring(0, temp.indexOf("#"));
                                String secondNo=temp.substring(temp.indexOf(":")+1);
                                linee.setSrc(Integer.parseInt(firstNo));
                                linee.setSrcNo(Integer.parseInt(secondNo));

                            }else if(line.contains("Name=\"Points\"")){
                                String temp=line.substring(line.indexOf("\"Points\">")+9, line.indexOf("</P"));
                                linee.setPoints(temp);
                                //System.out.println(temp);
                            }
                        }

                    }
                    fileStream.close();
                    //scanner.close();

                }
            }
            
            system.getStartPoint();
            system.getEndPoint();
            
            
            
//            System.out.println("\n\n************Blocks************");
//            for(int i=0;i<system.blocks.size();i++){
//                system.blocks.get(i).printBlock();
//            }
//            System.out.println("\n\n************Lines************");
//            for(int i=0;i<system.lines.size();i++){
//                //system.lines.get(i).printLine();
//                //system.lines.get(i).printBranches();
//            	system.lines.get(i).printPoints();
//            	system.lines.get(i).printPoint();
//            }
        }


        catch(NotValidSimulinkFile ex){
            System.out.println("NotValidAutosarFile"); //If the file is not .mdl
        }
        catch(IOException ex){
            System.out.println("IO Error");            //I don't really remember
        }
        catch(EmptyFile ex){
            System.out.println("File is Empty");       //If the file is empty
        }
		return system;
    }
    public static void main(String[] args) throws Exception {
        read("E:/FEASU/Spring semester 2023 - On/Advanced Programming/Project/Project New/src/Example.mdl");
    }
}