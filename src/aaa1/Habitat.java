package aaa1;

import java.util.Random;

public final class Habitat 
{
    Population pop;
    int max=200;
    double error=7.5;
    public Habitat(int size)
    {        
        pop=new Population(size,(short)0);
        circleOfLife();
    }
    public void circleOfLife()
    {
        Population p;
        do
        {
            p=new Population(pop.singles.length, (short) (pop.n+1));
            for(int i=0;i<pop.singles.length;i++)
            {
                Single s=scan(pop);
                s=mutation(woohoo(s, scan(pop,s)));
                p.singles[i]=s;
            }
            pop=p;
            pop.order();
        }while(adaptation(pop));
        System.out.println(pop.singles[0].toString()+"Numero de generaciones = "+pop.n);
    }
    public Single scan(Population p)
    {
        
        double r=Math.random();
        double prob=0;
        for(int i=0;i<pop.singles.length;i++)
        {
            if(r>=prob&& r<prob+ (double)(p.singles.length-i)/p.pon)
                return p.singles[i];
             prob =prob+ (double)(p.singles.length-i)/p.pon;
        }
        return p.singles[0];
    }
    public Single scan(Population p,Single s)
    {
        double r=Math.random();
        double prob=0;
        for(int i=0;i<pop.singles.length;i++)
        {
            prob = prob+ (double)(p.singles.length-i)/p.pon;
            if(r<prob&&p.singles[i]!=s)
                return p.singles[i];
        }
        if(p.singles[0]!=s)
            return p.singles[0];
        return p.singles[1];
    }
    public Single woohoo(Single s1,Single s2)
    {
        boolean DNA[]=new boolean[16];
        Random w=new Random();
        for(int i=0;i<16;i++)
            if(w.nextBoolean())
                DNA[i]=s1.DNA[i];
            else
                DNA[i]=s2.DNA[i];
        return new Single(DNA);
    }
    public Single mutation(Single s)
    {
        double r=Math.random();
        Random w=new Random();
        if(r<1/1000)
            do
            {
                int n=w.nextInt(16);
                s.DNA[n]=!s.DNA[n];
            }while(w.nextBoolean());
        return s;
    }
    public boolean adaptation(Population p)//calcula la varianza
    {
        if(p.n>=max)//Si se alcanzo el maximo de generaciones, terminar el ciclo
            return false;
        double x=0,y=0,var;
        for (Single single : p.singles) //Sumatoria de todos los individuos en la poblacion
            x = x + single.value;
        x=x/p.singles.length;//Media de todos los individuos en la poblacion
        for (Single single : p.singles) 
            y = y + Math.pow(single.value - x, 2);
        var=y/(pop.singles.length);
        var=Math.sqrt(var);
        //si la varianza es menor a a la aceptable para que siga habiendo cambios entre geberaciones, termina el ciclo
        return var >= error;
    }    
}
