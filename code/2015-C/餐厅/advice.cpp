#include "res.h"

void advice()
{
    char a[100];
	printf("\n\n\n\t\t\t���Ľ����Ͷ��:\n"); 
	scanf("%s",a);
	FILE *fp=fopen("advice.txt","a");
	fprintf(fp,"%s\n",a);
	fclose(fp);

}
