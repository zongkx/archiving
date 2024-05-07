#include"supermarket.h"



void look()
{	
    system("cls");
    FILE *fp;
	point p[100];

	int i,sum=0,n;
	fp=fopen("supermarket.txt","r+");
    for(i=0;i<100;i++)
	{ 
	    p[i].id=0;
		fscanf(fp,"%d%s%lf%lf%d%s",&p[i].id,p[i].name,&p[i].iprice,&p[i].oprice,&p[i].number,p[i].itime );
		if(p[i].id==0)
	 	   { break; }
		sum++;
    }
    QuickSort(p,sum);    //快速排序
    printf("下面是仓库库存按从小到大排序的货物清单！ \n 编号     名称      进价    售价    库存    入库时间\n ");
	for(i=0;i<sum;i++)
	{
		printf("\t\t%d       %s       %.2f       %.2f       %d     %s\n",p[i].id,p[i].name,p[i].iprice,p[i].oprice,p[i].number,p[i].itime );
	}   

	fclose(fp); 
}


int Partition(point k[],int low,int high)
{
	int po;
	
	point a;
	
	a.id=k[low].id;//
	a.iprice=k[low].iprice;
	a.oprice=k[low].oprice;
	strcpy(a.itime,k[low].itime );
	strcpy(a.name ,k[low].name); 
	
	
	 
	
	po = k[low].number;
	while(low < high)
	{
		while(low < high && k[high].number >= po)
		{
			high--;
		}
		k[low].number = k[high].number;//
		k[low].iprice = k[high].iprice;
		k[low].id = k[high].id;
		k[low].oprice = k[high].oprice;
		strcpy(k[low].name,k[high].name);
		strcpy(k[low].itime,k[high].itime);
		
		k[low].id=k[high].id;
		
		while(low < high  && k[low].number <= po)
		{
			low++;	
		}
		k[high].number = k[low].number;//
		k[high].iprice = k[low].iprice;
		k[high].id = k[low].id;
		k[high].oprice = k[low].oprice;
		strcpy(k[high].name,k[low].name);
		strcpy(k[high].itime,k[low].itime);
		k[high].id=k[low].id;
		
		
			
	}
	k[low].id=a.id;//
	k[low].number = po;
    k[low].iprice=a.iprice;
	k[low].oprice=a.oprice;
	strcpy(k[low].itime,a.itime );
	strcpy(k[low].name,a.name ); 
	
	
	
	return low;	
}

void Qsort(point k[], int low, int high)
{
	int po;
	if(low < high)
	{
		po = Partition(k,low,high);
		Qsort(k,low,po-1);
		Qsort(k,po+1,high);
	}
}

void QuickSort(point k[],int n)
{
	Qsort(k,0,n-1);
}



