package aaa1;

public class Population
{
    Single singles[];
    short n;
    int pon;
    public Population(int m,short n)
    {
        this.n=n;
        singles=new Single[m];
        for(int i=1;i<singles.length+1;i++)
            pon=pon+i; 
        if(n==0)
        {
            for(int i=0;i<m;i++)
            {
                Single s=new Single(16);
                singles[i]=s;
            }
            order();
        }
    }
    public void order()    
    {
        boolean w;
        if(singles!=null)
        do{
            w=false;
            for(int i=0;i<singles.length-1;i++)
            {
                if(singles[i].fitness<singles[i+1].fitness)
                {
                    w=true;
                    Single s=singles[i];
                    singles[i]=singles[i+1];
                    singles[i+1]=s;
                }
            }
        }while(w);
        int stop=0;
    }
    
}
