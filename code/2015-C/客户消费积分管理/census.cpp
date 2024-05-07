#include"bank.h"


void census()
{
    system("cls");
  //临时存储bankrecord里面的数据 
	int i;
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
	
	/*创建时间生成函数*/ 
	time_t ctime;
    struct tm *timeinfo;
    time(&ctime);
    timeinfo=localtime(&ctime);
    int h,b,g;
    int yearr[10],monn[3],dayy[3];
    b =timeinfo->tm_mon+1;
    g =timeinfo->tm_mday;
    FILE *fp=fopen("bankrecord.txt","r");
	int sum=0;
    
	for(i=1;i<100;i++)
	{
		fscanf(fp,"%ld%d%d%d%d %c%d%d",&nac[i],&nyear[i],&nmonth[i],&nday[i],&nm1[i],&nmark[i],&n[i],&nm2[i]);
		if(nmonth[i]==b&&nday[i]>=1&&nday[i]<=31)
		{
			
			if(nmark[i]=='+')
		      sum=sum+n[i];
		    else
			  sum=sum-n[i];  
		}
		if(nyear[i]==0)
		  break;
		
	}
	printf("\n\n\t\t截止到目前本月所有的发生额的正负值为 ： ");
	printf("%d\n\n\n\t**********************************************************\n",sum);
	fclose(fp);
	
	
	/*临时存储文件内数据的数组*/ 
    char newname[100][100];//姓名 
	char newctime[200][200];//创建时间 
	char newdtime[200][200];//销户时间 
	char newpassword[200][200];//密码
	long newac[200];//账号 
	long newid[200];//身份证号码 
	int newmoney[200];//余额 
	int newmark[200]={0};//标记 
    int newmark1[200]; 
	
	FILE *fp1;
	fp1=fopen("saveuser.txt","r+");
	int k=0;h=0;
	for(i=1;i<100;i++)
	{
		fscanf(fp,"%s%s%s%s%ld%ld%d%d%d",newname[i],newctime[i],newdtime[i],newpassword[i],&newac[i],&newid[i],&newmoney[i],&newmark[i],&newmark1[i]);
		if(newmark[i]==0)
		   { break; }
	    k++;h=h+newmoney[i];
	    
}
    fclose(fp1); 
	int r=h/k;
	printf("\t**********************************************************\n\n"); 
	printf("\t\t截至到目前银行用户总数： ");printf("%d\n",k);
	printf("\t\t          用户余额总值:   ");printf("%d\n",h);
	printf("\t\t          平均用户余额:   ");printf("%d\n",r);
	 
	
}
