#include"bank.h"

void manager()
{
	
	system("cls");
PRN: while(1)
{
 
	printf("---------------���������Ա����-----------------\n");
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
		printf("--------------------------������ȷ-------------------------------\n");
		system("cls");
		break;
	}
    else 
	    printf("-------------------�������,������ٴ�����-------------------------\n");
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
	    
	printf("\n\n��������Ա�����밴 1�������ܽ����밴 0 \n");
	int b;
	scanf("%d",&b);
	if(b==1)
	{
		goto PRNS;
	}  
	   
}
