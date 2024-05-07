#include "res.h"

void advice()
{
    char a[100];
	printf("\n\n\n\t\t\t您的建议或投诉:\n"); 
	scanf("%s",a);
	FILE *fp=fopen("advice.txt","a");
	fprintf(fp,"%s\n",a);
	fclose(fp);

}
