#include"res.h"


void server()
{
	while(1)
	{
		system("cls");
		play1();
		int a;
		cin>>a;
		if(a==1)
		   enter();
		    
		if(a==2)
		   menu1();
		   system("cls");
		if(a==3)
		   order();
		   system("cls");
		if(a==0)
		  cout<<"谢谢使用服务员服务系统！\n";
		  break;
		     
	}
	
	
	
	
	
	
	
	
	
	
}
