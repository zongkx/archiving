#include "res.h"


int main ()
{ 
     system("color 3e");
     system("title ~~欢迎来到餐厅点餐系统~~");
	 while(1)
	{
	        int a;
	        display();//总界面 
	        scanf("%d",&a);
		    if(a==1) 
			  manager();//管理员界面 
			  system("cls");
            if(a==2)
		      server();//服务员界面   
              system("cls");
            if(a==3)
			  selfhelp();//顾客自助服务界面 
			  system("cls");  
            if(a==0)
		      break;//退出 
	}   
	    
}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


