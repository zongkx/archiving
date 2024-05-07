#include"supermarket.h"

int main()
{
  while(1)
  {
	int a,b;
	display();
	scanf("%d",&a);
	if(a==1) 
		manager();
	    system("cls");
    if(a==0)
		break;
  }    
  return 0;
}        

