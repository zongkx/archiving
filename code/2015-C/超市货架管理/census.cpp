#include"supermarket.h"


void census()
{
    system("cls");
	FILE *fp;
	fp=fopen("record.txt","r+");
	int sum;
    int id[100],s[100][100],a[100],n[100];
    printf("�����ǻ���ȡ����¼\n id      ʱ��     ȡ����      ����\n "); 
	for(int i=1;i<1000;i++)
	{
		id[i]=0;
		fscanf(fp,"%d%s%d%d",&id[i],s[i],&a[i],&n[i]);
		if(id[i]==0)
		  break;
		printf("%d %s %d %d\n",id[i],s[i],a[i],n[i]);  
	}
	

}

