#include"res.h" 

/*������Ŀͳ��*/ 
void cen()
{
	system("cls");
	/*����ʱ�����ɺ���*/ 
	time_t ctime;
    struct tm *timeinfo;
    time(&ctime);
    timeinfo=localtime(&ctime);
    int a,b,c;
    int yearr[10],monn[3],dayy[3];
    a=timeinfo->tm_year+1900;
    b=timeinfo->tm_mon+1;
    c=timeinfo->tm_mday;
    int sum=0,sum1=0;
	
	int year[100]={1};
	int month[100];
	int day[100];
	int s[100];
	int i;
	FILE *fp=fopen("acount.txt","r");
	for(i=1;i<100;i++)
	{  
	    fscanf(fp,"%d%d%d%d",&year[i],&month[i],&day[i],&s[i]);
	    if(year[i]==a&&month[i]==b-1)
	        sum=sum+s[i];
		if(year[i]==a&&month[i]==b&&day[i]<=c)  
	        sum1=sum1+s[i];   
	    if(year[i]==0 )
	       {break;}
	}

    printf("\t\t�ϸ��·������Ӫҵ���ǣ�%d\n",sum);

    printf("\t\t��ֹ�����ڱ��µ�Ӫҵ���ǣ�%d\n",sum1);
    fclose(fp);
    system("pause");
}
