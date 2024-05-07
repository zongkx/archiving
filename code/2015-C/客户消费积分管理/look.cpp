#include"bank.h"


void look(long a)

{	int i;
    system("cls");
	//临时存储bankrecord里面的数据 
	long nac[100];
	int nyear[100];
	int nmonth[100];
	int nday[100];
	int  nm1[100];//之前余额 
	char nmark[100];
	int  n[100];//发生额 
	int	 nm2[100];//当前余额 
	for(i=1;i<100;i++){
		nac[i]=0;
	}
	
	int vyear,vmonth,vday,uyear,umonth,uday;
    printf("请输入起始日期\n");
	scanf("%d%d%d",&vyear,&vmonth,&vday);
	printf("请输入截至日期\n"); 
	scanf("%d%d%d",&uyear,&umonth,&uday);
	int k,j;
	k=vyear*10000+vmonth*1000+vday;
	j=uyear*10000+umonth*1000+uday;
	
	
	FILE *fp=fopen("bankrecord.txt","a+");
    printf("\t 存取款日期       +/-         发生额           当前余额    \n\n");
	for(i=1;i<100;i++)
	{
		fscanf(fp,"%ld%d%d%d%d %c%d%d",&nac[i],&nyear[i],&nmonth[i],&nday[i],&nm1[i],&nmark[i],&n[i],&nm2[i]);
		
		if(a==nac[i]&&k<=nyear[i]*10000+nmonth[i]*1000+nday[i]&&nyear[i]*10000+nmonth[i]*1000+nday[i]<=j)
		   printf("\t%d-%02d-%02d         %c          %d             %d\n",nyear[i],nmonth[i],nday[i],nmark[i],n[i],nm2[i]);
		if(nyear[i]==0)
		  break;
		
	}
	
	
	
}
