#include"supermarket.h"

void record(int did,int a ,int n)
{
	system("cls");
    char b[20];
    FILE *fp;
	fp=fopen("record.txt","a+");
    b[20]=time(b);
	fprintf(fp,"%d %s %d %d\n",did,b,n,a);
	fclose(fp);
	
}
