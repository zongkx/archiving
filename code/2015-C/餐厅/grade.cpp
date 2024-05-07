#include "res.h"

void grade()
{
	char a;
	int b;
	cout<<"欢迎使用菜品评分系统！"<<endl;
PRN:	printf("评分分为A/B/C三个档\n请输入菜的编号：");
    cin>>b;
	cout<<"请输入你的评价："; 
	cin>>a;
	FILE *fp=fopen("grade.txt","a");
	fprintf(fp,"%d %c\n",b,a);
	fclose(fp);
	int i;
	cout<<"继续打分请按 1  退出 0"<<endl;
	cin>>i;
	if(i==1)
	  goto PRN; 
	
	
	
	
	
}
