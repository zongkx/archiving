#include "sum.h"


int main ()
{ 
     //system("color 3E");
     system("title ~~欢迎来到客户消费管理系统~~");
	 while(1)
	{
	        int a;
	        display();//总界面 
	        scanf("%d",&a);
		    if(a==1) 
			  create();// 添加 
			  system("cls");
            if(a==2)
		      serch();//查询 
              system("cls");
            if(a==3)
		      change();//修改 
              system("cls");
            if(a==4)
			 remove();// 删除 
			  system("cls"); 
			if(a==5)
		      sub();//计算积分折扣 
              system("cls");
            if(a==6)
		      sort();//积分排序 
              system("cls");
            if(a==0)
		      break;//退出 
	}   
	    
}
		
