package aaa1;

import java.util.Random;

public class Single 
{
    boolean DNA[];
    short fitness;
    
    int value,size;
    public Single(int size)       
    {
        this.size=size;
        DNA=new boolean[size];
        Random r=new Random();
        for(int i=0;i<size;i++)
            DNA[i]=r.nextBoolean();  
        selfsteem();
    }
    public Single(boolean code[])
    {
        DNA=code;
        selfsteem();
    }
    public void selfsteem()
    {
        fitness=0;
        value=0;
        size=DNA.length;
        for(int i=0;i<size;i++)
        {
            if(DNA[i])
                value=(int) (value+(Math.pow(2, (size-1)-i)));
            if(i<(size-1)&&DNA[i]==true&&DNA[i+1]==false)
                fitness++;
        }
    }
    @Override
    public String toString()
    {
        String s="Estado = ";
        for(int i=0;i<size;i++)
        {
            if(DNA[i])
                s=s+"1";
            else
                s=s+"0";
        }
        s+="\r\nFitness de la mejor solucion = "+fitness+"\r\n";
        return s;
    }
}
