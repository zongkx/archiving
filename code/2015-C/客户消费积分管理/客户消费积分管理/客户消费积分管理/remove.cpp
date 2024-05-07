#include "sum.h"


//删除客户信息 
void remove()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n请输入您要删除顾客的会员号:"<<endl;
	char id[6];
	cin>>id;
    linklist p,p0,p1=head; 
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
	    p=p1->next; }
	    
	   	printf("请确认您的当前信息\n");
	   	cout<<" 1.姓名："<<p->a->name<<endl;
	   	cout<<" 2.会员卡号："<<p->a->ID<<endl;
	   	cout<<" 3.消费金额："<<p->a->consume<<endl;
	   	cout<<" 4.积分："<<p->a->s<<endl;
	   	cout<<" 5.折扣："<<p->a->y<<endl;
	   	
	  	p0=p->next;p->next=NULL;
	    p1->next=p0;
	     	
	FILE *fp;
    fp=fopen("costomer.txt","w");
    
    linklist k=head->next; 
	while(k)
	{
	    fprintf(fp,"%s %s %.2f %.1f %.1f\n",k->a->name,k->a->ID,k->a->consume,k->a->s,k->a->y);
	    k=k->next; 
    }
	fclose(fp);

	   
	    cout<<"\n恭喜您删除功！\n";
    
	  cout<<"返回菜单请按0：" ;
     int a;cin>>a;	
	    if(a==0)
        display();
        
	
	   
	   
}
