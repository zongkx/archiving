#include"sum.h"

//查询客户信息 
void serch()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n请输入您的会员卡号:"<<endl;
	char id[6];
	scanf("%s",id);
    linklist p,p1=head; 
    while(p1->next&&strcmp(p1->next->a->ID,id)!=0)
       {
       	 p1=p1->next;//当前结点不是，指针后移 
	   }
    if (p1->next==NULL)
	   {
	   	printf("输入错误，请重新输入！\n");
	   	goto PRN;
	   } 
	else
	{  
	    p=p1->next; 
	   	printf("您查询的信息如下\n");
	   	cout<<"姓名："<<p->a->name<<endl;
	   	cout<<"会员卡号："<<p->a->ID<<endl;
	   	cout<<"消费总金额："<<p->a->consume<<endl;
	   	cout<<"当前积分："<<p->a->s<<endl;
	   	cout<<"当前折扣："<<p->a->y<<endl;
        printf("继续查询 1   返回菜单 0:"); 
	   	int a;cin>>a;
     	if(a==1)
	    serch();
    	if(a==0)
        display();
    }
	  
}
