import java.util.*;
import java.io.*;

public class Algorithm{

    StateBridge maxE;
    int max_score;
    int time=0;
    public int total_time=0;

    ArrayList <StateBridge> path = new ArrayList <StateBridge>();


    ArrayList <StateBridge> metopo = new ArrayList <StateBridge>(); 


    StateBridge root;

    public Algorithm(){}

    public Algorithm(StateBridge root){
        this.root=root;
    }

    public StateBridge Astar(){
        metopo.add(new StateBridge(root));
        StateBridge current=new StateBridge(root);
        StateBridge s1=new StateBridge();
        int i=0;//pointer thesis listas ntime
        int x=1;
        int w=0;
        int y=0;
        if(current.getN()==1){//an dothei mono enas anthropos
            //current.N=0;
            //den bazo to child tou perito gia tr
            current.getLeft().add(current.Right.get(0));
            current.getRight().remove(0);
            total_time=current.getLeft().get(0);
            current.setFather(root);
            return current;
        }
        while (current.getN()>0){ 
            int j=0;
            while (j<current.getN()*(current.getN()-1)/2){
                for (int k=0; k<current.getN()-x; k++){  
                    StateBridge s= new StateBridge(current); //copy constructor
                    s.getLeft().add(current.getRight().get(i));
                    s.getLeft().add(current.getRight().get(k+i+1));
                    s.time+=Math.max(current.getRight().get(i),current.getRight().get(k+i+1));
                    s.getRight().remove(i);
                    s.getRight().remove(k+i); 
                    s.setFather(current);
                    metopo.add(new StateBridge(s));
                    current.getChild(s);
                    
                    w=k;
                    
                }//for (1-3,1-6,1-8,1-12 proto for)
                
                j+=w+1;
                //j+=w-1; //paidia poy exoyyn ftiaxtei kaanei kai j++ meta sto for
                i+=1; 
                x++; 

            }//while
            metopo.remove(y); //pointer thesi current
            //euretiki sto metopo
            for(int a=0; a<metopo.size();a++){
                metopo.get(a).heuristic1(); //ypologizei ta score gia StateBridge(metopo)
                if (metopo.get(a).isTerminal()){//elegxei an einai teliki
                    metopo.get(a).setN(0);
                    total_time=metopo.get(a).time;
                    max_score=metopo.get(a).getScore()+total_time;//mikrotero score apo ola ta metopa
                    maxE=metopo.get(a);//apo poy proirthe to mikrotero skor (STATE BRIDGE)
                    y=a;// se poia thesi(sto metopo)briskete to mikrotero skor
                    break;
                }else{
                    if (a==0){//1i epanalipsi orise min
                        
                        max_score=metopo.get(a).getScore()+metopo.get(a).total_time;//mikrotero score apo ola ta metopa
                        maxE=metopo.get(a);//apo poy proirthe to mikrotero skor (STATE BRIDGE)
                        y=a;
                    }else{//sygkrine me min
                        if(metopo.get(a).getScore()+metopo.get(a).total_time<max_score){
                            max_score=metopo.get(a).getScore()+metopo.get(a).total_time;
                            maxE=metopo.get(a);
                            y=a;
                        }
                    }
                }
                
            }//min=elaxisto skor=elaxisto athoisma people_left apo kathe antikeimeno
           
            //exv epileksei maxE na proxorisv
            s1=new StateBridge(maxE); //stoixeio apo euretiki poy anikei sto metopo == s1

            current=new StateBridge(s1);
            if (current.getN()!=0){//an den exeis brei teliki katastasi
                //moveRight
                StateBridge s2=new StateBridge(s1);
                s1.moveRight(s1,y,s2,metopo); //time+ & metopo.add exei ginei sto moveRight
                y=metopo.size()-1; //thesi s2 sto metopo
                s2.setN(s2.getN()-1);//kathe 2i katastasi meionete to N px apo 4 se 3
                current=new StateBridge (s2);
                i=0;
                x=1; //arxikopoiisi gia nea paidia (N-x)
            }//if
        }//while
        return current;// teliki katastasi poy epistrefei
    }

    

    public void print(StateBridge x){
       
        ArrayList <StateBridge> path = new ArrayList<StateBridge> (); //diadromi apo to ti teliki katastasi mexri to root (apo paidi se father)
        path.add(x);//prosthetoyme tin teliki katastasi apo main
        StateBridge father=x.getFather(x);
        while (father!=null){
            path.add(father);
            father=father.getFather(x);
        }
        for (int i=path.size()-1;i>=0;i--){ //anapodo prin oste ektiposi apo riza se teliki katastasi
            System.out.println(path.size()-i+"o Bima");
            System.out.println(path.get(i).Left+"   |================|  "+path.get(i).Right);
        }
        System.out.println("");
        System.out.println("Total time: "+total_time); //sinolikos xronos metakinisis
        System.out.println("THE END");
    }
}