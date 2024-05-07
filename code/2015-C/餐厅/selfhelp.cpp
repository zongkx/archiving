#include"res.h"

void selfhelp()

{
  while(1)
  {
  	system("cls");
	play3();
	int a;
	cin>>a;
	if(a==1)
	   advice(); 
	   system("cls");
	if(a==2)
	   grade();
	   system("cls");
	if(a==0)
	   break;
   }
}
