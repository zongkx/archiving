#include"bank.h"

void manager()
{
	
	system("cls");
PRN: while(1)
{
 
	printf("---------------请输入管理员密码-----------------\n");
	char n[6],m[6];
	int x=0;
    strcpy(m,"123456");
	while(1){
               n[x]=getch();
               if(n [x]!='\r')
			       {printf("*"); ++x;}
               else break;
            }
    n [x]='\0';
    printf("\n");
			   
	if(strcmp(m,n)==0)
	{
		printf("--------------------------密码正确-------------------------------\n");
		system("cls");
		break;
	}
    else 
	    printf("-------------------密码错误,请检查后再次输入-------------------------\n");
		goto PRN;	
}	
PRNS: 
    system("cls");
	play0();
	int a;
    scanf("%d",&a);
	if(a==1)
	  create();
	if(a==2)
	  deleteuser();
	if(a==3)
	  acount();
	if(a==4)
	  loss();
	if(a==5)
	  census();   
	    
	printf("\n\n其他管理员操作请按 1，返回总界面请按 0 \n");
	int b;
	scanf("%d",&b);
	if(b==1)
	{
		goto PRNS;
	}  
	   
}
