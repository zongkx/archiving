#include "res.h"


void look2()
{
	system("cls");
	int a[10];
	char b[20];
	strcpy(b,"qqqqqqqqqqqqqqqqqqq");
	FILE *fp=fopen("grade.txt","r");
	printf("\t\t²ËµÄ±àºÅ                     ÆÀ·Ö\n"); 
	int i;
	for(i=0;i<50;i++)
	{
		fscanf(fp,"%d %c",&a[i],&b[i]);
		if(b[i]=='q')  
		{break;	}
		
		printf("\t\t%d                              %c\n",a[i],b[i]);
	}
	int d;
	cin>>d;
	
	
	
	
 } 

	
	
	
	
 
