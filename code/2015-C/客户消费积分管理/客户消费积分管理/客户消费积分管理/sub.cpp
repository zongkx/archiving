#include"sum.h"

//统计积分及折扣
void sub()
{
	system("cls");
    linklist head;	
	read(head);//读取函数 

PRN:    cout<<"\n请输入您的会员卡号:"<<endl;
	char id[6];
	scanf("%s",id);
    linklist p,p1=head;
    while(p1->next&&strcmp(p1->next->a->ID ,id)!=0)
       {
       	 p1=p1->next;//当前结点不是，指针后移 
	   }
    if (p1==NULL)
	   {
	   	printf("输入错误，请重新输入！\n");
	   	goto PRN;
	   } 
    else
	{ 
	  p=p1->next;
	  printf("您之前的积分为%.1f\n",p->a->s) ;
      double y; 
      y=display_discount(p->a->s);
	
      printf("根据您的您的积分您本次的折扣为%.1f折\n",y);
      printf("请输入您本次消费金额：\n") ;
      double m,a,money;cin>>m;
      money=m*y/10 ;
      printf("您本次的折扣价为：%.1f\n",money);
      a=(int)money;
      printf("本次积分：%.1f\n",a);
      p->a->s+=a;
      p->a->consume+=money;
     
  
	  FILE *fp2;
      fp2=fopen("costomer.txt","w");
      linklist q1=head->next;
      while(q1)
	{
	  fprintf(fp2,"%s %s %.2f %.1f %.1f\n",q1->a->name ,q1->a->ID ,q1->a->consume ,q1->a->s,q1->a->y);
	  q1=q1->next ;
	}
    fclose(fp2);
    
    cout<<"返回菜单请按0：" ;
     int d;cin>>d;	
	    if(d==0)
        display();
}
}
