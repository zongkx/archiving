#include "sum.h"


int main ()
{ 
     //system("color 3E");
     system("title ~~��ӭ�����ͻ����ѹ���ϵͳ~~");
	 while(1)
	{
	        int a;
	        display();//�ܽ��� 
	        scanf("%d",&a);
		    if(a==1) 
			  create();// ��� 
			  system("cls");
            if(a==2)
		      serch();//��ѯ 
              system("cls");
            if(a==3)
		      change();//�޸� 
              system("cls");
            if(a==4)
			 remove();// ɾ�� 
			  system("cls"); 
			if(a==5)
		      sub();//��������ۿ� 
              system("cls");
            if(a==6)
		      sort();//�������� 
              system("cls");
            if(a==0)
		      break;//�˳� 
	}   
	    
}
		
