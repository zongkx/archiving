#include"res.h"


void manager()
{
PRN:while(1)
{   
	printf("\t\t\t-----------���������Ա����----------\n");
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
		printf("---------------������ȷ-----------------\n");
		system("cls");break;
		
		
	}
    else 
	    printf("------------�������,������ٴ�����-------------\n");
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
