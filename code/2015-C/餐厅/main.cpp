#include "res.h"


int main ()
{ 
     system("color 3e");
     system("title ~~��ӭ�����������ϵͳ~~");
	 while(1)
	{
	        int a;
	        display();//�ܽ��� 
	        scanf("%d",&a);
		    if(a==1) 
			  manager();//����Ա���� 
			  system("cls");
            if(a==2)
		      server();//����Ա����   
              system("cls");
            if(a==3)
			  selfhelp();//�˿������������ 
			  system("cls");  
            if(a==0)
		      break;//�˳� 
	}   
	    
}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


