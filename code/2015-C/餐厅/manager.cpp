#include"res.h"


void manager()
{
PRN:while(1)
{   
	printf("\t\t\t-----------请输入管理员密码----------\n");
	char n[8],m[8];
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
		printf("---------------密码正确-----------------\n");
		system("cls");break;
		
		
	}
    else 
	    printf("------------密码错误,请检查后再次输入-------------\n");
		goto PRN;
}

	while(1)
	{
		system("cls");
		play0();
		int a;
		scanf("%d",&a);
		  if(a==1)
		    create1();
		    system("cls");
		  if(a==2)
		    menu1();
		    system("cls");
		  if(a==3)
		      look1();
		      system("cls");	   
		  if(a==4)
		    sta();
			system("cls");
	      if(a==5)
		     look2();
			 system("cls");		   
		  if(a==6)
		      cen();
		      system("cls");
		  if(a==0)
		    break;    
		
	}
	
	
	
	
	
	
}
