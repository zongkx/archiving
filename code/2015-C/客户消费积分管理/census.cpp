#include"bank.h"


void census()
{
    system("cls");
  //��ʱ�洢bankrecord��������� 
	int i;
	long nac[100];
	int nyear[100];
	int nmonth[100];
	int nday[100];
	int  nm1[100];//֮ǰ��� 
	char nmark[100];
	int  n[100];//������ 
	int	 nm2[100];//��ǰ��� 
	for(i=1;i<100;i++){
		nac[i]=0;
	}
	
	/*����ʱ�����ɺ���*/ 
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
	printf("\n\n\t\t��ֹ��Ŀǰ�������еķ����������ֵΪ �� ");
	printf("%d\n\n\n\t**********************************************************\n",sum);
	fclose(fp);
	
	
	/*��ʱ�洢�ļ������ݵ�����*/ 
    char newname[100][100];//���� 
	char newctime[200][200];//����ʱ�� 
	char newdtime[200][200];//����ʱ�� 
	char newpassword[200][200];//����
	long newac[200];//�˺� 
	long newid[200];//���֤���� 
	int newmoney[200];//��� 
	int newmark[200]={0};//��� 
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
	printf("\t\t������Ŀǰ�����û������� ");printf("%d\n",k);
	printf("\t\t          �û������ֵ:   ");printf("%d\n",h);
	printf("\t\t          ƽ���û����:   ");printf("%d\n",r);
	 
	
}
