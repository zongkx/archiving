#include "sum.h"


//ɾ���ͻ���Ϣ 
void remove()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n��������Ҫɾ���˿͵Ļ�Ա��:"<<endl;
	char id[6];
	cin>>id;
    linklist p,p0,p1=head; 
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
	    p=p1->next; }
	    
	   	printf("��ȷ�����ĵ�ǰ��Ϣ\n");
	   	cout<<" 1.������"<<p->a->name<<endl;
	   	cout<<" 2.��Ա���ţ�"<<p->a->ID<<endl;
	   	cout<<" 3.���ѽ�"<<p->a->consume<<endl;
	   	cout<<" 4.���֣�"<<p->a->s<<endl;
	   	cout<<" 5.�ۿۣ�"<<p->a->y<<endl;
	   	
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

	   
	    cout<<"\n��ϲ��ɾ������\n";
    
	  cout<<"���ز˵��밴0��" ;
     int a;cin>>a;	
	    if(a==0)
        display();
        
	
	   
	   
}
