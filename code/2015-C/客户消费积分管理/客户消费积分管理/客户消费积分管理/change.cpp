#include "sum.h"


//�޸Ŀͻ���Ϣ 
void change()
{
	system("cls");
	linklist head;
	read(head);    
PRN:cout<<"\n���������Ļ�Ա����:"<<endl;
	char id[6];
	cin>>id;
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
		}
	    
	   	printf("��ȷ�����ĵ�ǰ��Ϣ\n");
	   	cout<<" 1.������"<<p->a->name<<endl;
	   	cout<<" 2.��Ա���ţ�"<<p->a->ID<<endl;
	   	cout<<"*3.���ѽ�"<<p->a->consume<<endl;
	   	cout<<"*4.���֣�"<<p->a->s<<endl;
	   	cout<<"*5.�ۿۣ�"<<p->a->y<<endl;
	   	printf("��ѡ������Ҫ�޸ĵ���Ŀ��(1 or 2)");
	   	int a;cin>>a;
     	if(a==1)
	    {
	    cout<<"�������޸ĺ��������";
	    char b[20];
	    scanf("%s",b) ;
	    strcpy(p->a->name,b);
	  
		}
    	if(a==2)
    	{
        cout<<"�������޸ĺ�Ļ�Ա�ţ�";
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
	cout<<" �޸���� 1  ���ز˵� 0��";
	    int d;	cin>>d;
     	if(d==1)
	    cout<<"��ϲ���޸ĳɹ���";
    	if(d==0)
        display();
	
	   
}
