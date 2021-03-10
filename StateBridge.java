import java.util.*;
import java.io.*;

public class StateBridge
{
    
    private int N;
    private int[] ntime;//xronoi anthropwn
    ArrayList <StateBridge> children = new ArrayList<StateBridge>();
    private StateBridge father;
    private int score; //euretikis
    int time=0; //sinolikos xronos kathe antikeimenou
    public int total_time=0;//telikos xronos

   

    ArrayList<Integer> Left=new ArrayList<Integer>(); //xronoi poy briskontai aristera
    ArrayList<Integer> Right=new ArrayList<Integer>(); //xronoi poy xriskontai deksia


    public StateBridge(int N,int[] ntime) //se lepta o xronos
    {
        this.N=N;
        this.ntime=ntime;
        for(int i = 0;i<N;i++){ //bazoume olous tous xronous deksia
            this.Right.add(ntime[i]);
        }

    }

    public StateBridge(StateBridge s) //se lepta o xronos
    {
        this.N=s.N;
        this.ntime=s.ntime;
        this.Right=new  ArrayList<Integer> (s.Right);
        this.Left=new ArrayList<Integer>(s.Left);
        this.time=s.time;
        this.father=s.father;
    }


    public StateBridge(){}

   
    public int getN()
    {
        return N;
    }
    
    public void setN(int n){
        this.N=n;
    }

    public int [] getntime()
    {
        return ntime;
    }

   

    public void getChild(StateBridge child){
        children.add(child);
    }

    public void setFather (StateBridge father){
        this.father=father;

    }

    public StateBridge getFather (StateBridge child){
        return father;
    }

    public ArrayList<Integer> getRight(){
        return this.Right;    
    }

    public void setRight(ArrayList<Integer> x){
        this.Right=x;    
    }

    public ArrayList<Integer> getLeft(){
        return this.Left;    
    }

    public void setLeft(ArrayList<Integer> x){
        this.Left=x;    
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score=score;
    }
   
    public boolean isTerminal(){
        if (this.Right.size()==0){
            return true;
        }else {
            return false;
        }
    }

    public void moveRight (StateBridge s,int y,StateBridge s2,ArrayList <StateBridge> metopo){//metakini anthrwpou apo left se right
        s2.setFather(s); 
        s.getChild(s2);       
        int min=s2.Left.get(0);
        int thesi=0;
        for(int i=1; i<s2.Left.size(); i++){//epilogi taxyteroy gia na gyrisei
            if (s2.Left.get(i)<min){
                min=s2.Left.get(i);
                thesi=i;
            }
        }
        s2.time+=min;
        s2.Right.add(s2.Left.get(thesi));
        s2.Left.remove(thesi);
        metopo.remove(y); //remove father toy s2
        metopo.add(s2);//add to child(s2)
    }
    
    public void heuristic1() {   //ypologizei score=athroisma people_move_left
        score=0;
        for(int i=0;i<this.Left.size();i++){
            score+=this.Left.get(i);
        }
    }

    
}