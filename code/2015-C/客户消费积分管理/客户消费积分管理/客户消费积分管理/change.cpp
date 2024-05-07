#include "sum.h"


//修改客户信息 
void change()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n请输入您的会员卡号:"<<endl;
	char id[6];
	cin>>id;
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
		}
	    
	   	printf("请确认您的当前信息\n");
	   	cout<<" 1.姓名："<<p->a->name<<endl;
	   	cout<<" 2.会员卡号："<<p->a->ID<<endl;
	   	cout<<"*3.消费金额："<<p->a->consume<<endl;
	   	cout<<"*4.积分："<<p->a->s<<endl;
	   	cout<<"*5.折扣："<<p->a->y<<endl;
	   	printf("请选择您需要修改的项目：(1 or 2)");
	   	int a;cin>>a;
     	if(a==1)
	    {
	    cout<<"请输入修改后的姓名：";
	    char b[20];
	    scanf("%s",b) ;
	    strcpy(p->a->name,b);
	  
		}
    	if(a==2)
    	{
        cout<<"请输入修改后的会员号：";
	    char c[6];
	    scanf("%s",c);
	    strcpy(p->a->ID,c);
		}
		
	FILE *fp;
    fp=fopen("costomer.txt","w");
    
    linklist k=head->next; 
	while(k)
	{
	    fprintf(fp,"%s %s %.2f %.1f %.1f\n",k->a->name,k->a->ID,k->a->consume,k->a->s,k->a->y);
	    k=k->next; 
    }
	fclose(fp);
	cout<<" 修改完毕 1  返回菜单 0：";
	    int d;	cin>>d;
     	if(d==1)
	    cout<<"恭喜您修改成功！";
    	if(d==0)
        display();
	
	   
}
