#include"sum.h"

//��ѯ�ͻ���Ϣ 
void serch()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n���������Ļ�Ա����:"<<endl;
	char id[6];
	scanf("%s",id);
    linklist p,p1=head; 
    while(p1->next&&strcmp(p1->next->a->ID,id)!=0)
       {
       	 p1=p1->next;//��ǰ��㲻�ǣ�ָ����� 
	   }
    if (p1->next==NULL)
	   {
	   	printf("����������������룡\n");
	   	goto PRN;
	   } 
	else
	{  
	    p=p1->next; 
	   	printf("����ѯ����Ϣ����\n");
	   	cout<<"������"<<p->a->name<<endl;
	   	cout<<"��Ա���ţ�"<<p->a->ID<<endl;
	   	cout<<"�����ܽ�"<<p->a->consume<<endl;
	   	cout<<"��ǰ���֣�"<<p->a->s<<endl;
	   	cout<<"��ǰ�ۿۣ�"<<p->a->y<<endl;
        printf("������ѯ 1   ���ز˵� 0:"); 
	   	int a;cin>>a;
     	if(a==1)
	    serch();
    	if(a==0)
        display();
    }
	  
}
