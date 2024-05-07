#include"bank.h"

int main()
{
  system("color 1c");
  system("title by:Paul"); 
  while(1){
	        int a,b;
	        display();
	        scanf("%d",&a);
		    if(a==1) 
			  manager();
			  system("cls");
            if(a==2)
		      selfhelp();    
              system("cls");
            if(a==0)
		      break;
		   }    
    return 0;
}        

