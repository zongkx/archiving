#include "sum.h" 

//按积分显示排序 
void sort()
{
	FILE *fp;
    fp=fopen("costomer.txt","a+");

    cosnode p[100];
    int i,sum=0;
	for(i=0;i<100;i++)
	{  
	   strcpy(p[i].name,"#");
	   fscanf(fp,"%s%s%lf%lf%lf",p[i].name,p[i].ID,&p[i].consume,&p[i].s,&p[i].y);
	   if(strcmp(p[i].name,"#")==0)
	     {break;}
	   sum++;
	}
	fclose(fp); 
	ShellSort(p,sum); 
	  printf("\n\n\t\t  按积分对客户排序如下：\n\n");
    printf("\t\t姓名    会员卡号     消费金额     积分      折扣\n");
    for(int i=0;i<sum;i++)
	{
		
		printf("\t\t%s      %s      %.1f      %.1f     %.1f\n",p[i].name,p[i].ID,p[i].consume,p[i].s,p[i].y );
	} 

	cout<<"返回菜单请按0：" ;
     int c;cin>>c;	
	    if(c==0)
        display();
}

//希尔排序 
void ShellSort(cosnode p[],int sum)
{
	int i,j,temp;
	int d = sum;
	do
	{
			d = d/4 + 1 ;
			for(i=d;i<sum;i++)
			{
				if(p[i].s< p[i-d].s)
				{
					temp = p[i].s;
					for(j = i-d ; p[j].s <temp && j>=0 ; j -= d)
					{
						p[j+d].s = p[j].s;
					}
					p[j+d].s = temp;
				}
			}
	}while(d > 1);
}
	

