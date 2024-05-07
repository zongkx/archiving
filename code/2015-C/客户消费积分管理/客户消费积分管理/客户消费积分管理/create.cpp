#include"sum.h"

//添加客户信息
void create()
{
	system("cls");
	linklist p; 
    p=new link;    /*申请空的节点空间*/ 
    FILE *fp=fopen("costomer.txt","a");//打开添加写文件
    cout<<"请输入姓名："<<endl;
	cin>>p->a->name;
	cout<<"请输入会员卡号：(6位)"<<endl;
	cin>>p->a->ID;
	cout<<"请输入本次消费金额："<<endl;
	cin>>p->a->consume;
	p->a->s=(int)p->a->consume;
	printf("本次积分为：%.1f\n",p->a->s);
	p->a->y=display_discount(p->a->s);
	cout<<"您的当前折扣为："<<p->a->y<<endl;
	p->next =NULL; 
    fprintf(fp,"%s %s %.2f %.1f %.1f\n",p->a->name ,p->a->ID ,p->a->consume,p->a->s,p->a->y);
    fclose(fp);
    printf("继续添加 1   返回 0:"); 
	int a;cin>>a;
	if(a==1)
	create();
	if(a==0)
    display();
}
