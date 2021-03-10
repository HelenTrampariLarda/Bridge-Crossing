import java.util.*;
import java.io.*;

public class main{
    
    public static void main(String []args){
        //parseInt metatropi string se int
        int N=Integer.parseInt(args[0]);//1o orisma N=plithos atomwn
        StateBridge root = new StateBridge();
        if(N>0){ 
            int [] ntime=new int[N];
            
            
            for(int i=1; i<args.length;i++){ // 2o ews n orismata xronwn kathe atomoy
                ntime[i-1]=Integer.parseInt(args[i]);
            }
        
            root = new StateBridge(N,ntime);
            Algorithm a=new Algorithm (root);
            a.Astar();
            a.print(a.Astar());
        }else{
            System.out.println("error: must N>0");
        }
    }
}

