#include"supermarket.h"
void change()
{
	system("cls");
    linklist head;
	move(head);
    
PRN:
	printf("�������Ƹ��� 1   ���۸��� 2    �ۼ۸��� 3\n"); 
	int a,b;
	cin>>a;
	char s[20];
	
	printf("�������޸ĵĻ���id\n");
	
    int did;
    scanf("%d",&did);
    linklist p2;
    linklist p1=head;
    while(p1->next&&p1->next->a->id!=did)
       {
       	 p1=p1->next;
	   }
	if(p1->next==NULL)
	{ 
	   cout<<"error";
	   goto PRN;
	}
    else
	{  
	p2=p1->next;
	 if(a==1) 
	    {cout<<"�������޸���Ϣ: ";
		 cin>>s;
		 strcpy(p2->a->name,s);
	    } 
	 if(a==2)
	     {cout<<"�����������ļ۸�: ";
		 cin>>b;
		 p2->a->iprice=b;
	     }
	 if(a==3)
	     {
	     cout<<"�����������ļ۸�: "	;
		 cin>>b;
		 p2->a->oprice=b;}	  	
	cout<<"�ɹ���"<<endl;       
    
	FILE *fp1;
    fp1=fopen("supermarket.txt","w");
    linklist q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%d %s %.2f %.2f %d %s\n",q1->a->id ,q1->a->name,q1->a->iprice,q1->a->oprice,q1->a->number,q1->a->itime);
		q1=q1->next ;
	}
    fclose(fp1);
   }
}
