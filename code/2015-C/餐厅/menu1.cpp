#include"res.h"


void menu1()
{
	int a;
	cout<<"\t��Ʒ���1   ��Ʒ���2    ���۸���3    ����0"<<endl;cin>>a;
 if(a==1)
    {
 PRN:      system("cls");
       menu *q;
 	   q=new menu;
	   
       FILE *fp=fopen("menu.txt","a+");	
	   cout<<"������˵ı��"<<endl;
	   cin>>q->no;
	   cout<<"������˵�����"<<endl;
	   cin>>q->mname;
	   cout<<"������˵ļ۸�"<<endl;
	   cin>>q->money;
	   q->mark=0;
	   q->next=NULL;
	   
	   fprintf(fp,"%d %s %d %d\n",q->no ,q->mname ,q->money,q->mark );
	   fclose(fp);
	   cout<<"�����ɹ�����ӭ�´�ʹ��"<<endl;
    	cout<<"������� 1  �����ϼ��˵� 0"<<endl;
	    int d;
    	cin>>d;
    	cout<<endl;
    	if(d==1)
	      {goto PRN;} 
	}	
 if(a==2)
    {
PRNA:  system("cls");
   	int i;
	menu *head,*rear;
	menu *q;
	
	head=(menu *)malloc(sizeof(menu));
	head->next=NULL;
	rear=head;
    FILE *fp;
	fp=fopen("menu.txt","a+");
	
	char nname[20][20];
	int nno[20]={0};
	int nmoney[20];
	int  nmark[20];
	for(i=1;i<100;i++)
   	{   
   	    fscanf(fp,"%d%s%d%d",&nno[i],nname[i],&nmoney[i],&nmark[i] );
   	     if(nno[i]==0)
		    break;
		q=(menu *)malloc(sizeof(menu));   
		q->no  =nno[i];
		strcpy(q->mname ,nname[i]);
		q->mark=nmark[i];
		q->money =nmoney[i];
		rear->next=q;
		rear=q;   
	}
	rear->next=NULL; 
	fclose(fp);
	printf("\t\t���       ����       �۸�        ��˱��\n");

    menu *k;
	k=head->next  ;
	
	while(k)
	{
		printf("\t       %d            %s          %d           %d\n",k->no,k->mname,k->money,k->mark);
		k=k->next ;
	}
	cout<<endl;cout<<" �����ϼ��˵� 0"<<endl;int d;cin>>d;
	
	
	}
    	
if(a==3)
   {
PRND:	system("cls");
   	int i;
	menu *head,*rear;
	menu *q;
	
	head=(menu *)malloc(sizeof(menu));
	head->next=NULL;
	rear=head;
    FILE *fp;
	fp=fopen("menu.txt","a+");
	
	char nname[20][20];
	int nno[20]={0};
	int nmoney[20];
	int  nmark[20];
	for(i=1;i<100;i++)
   	{   
   	    fscanf(fp,"%d%s%d%d",&nno[i],nname[i],&nmoney[i],&nmark[i] );
   	     if(nno[i]==0)
		    break;
		q=(menu *)malloc(sizeof(menu));   
		q->no  =nno[i];
		strcpy(q->mname ,nname[i]);
		q->mark=nmark[i];
		q->money =nmoney[i];
		rear->next=q;
		rear=q;   
	}
	rear->next=NULL; 
	fclose(fp);
	
	
	cout<<"������˵ı��"<<endl;
	int aid;
	cin>>aid;
	
	
	menu * p2;
    menu * p3=head;
while(p3!=NULL&&p3->next->no!=aid)
       {
       	 p3=p3->next;
	   }
	if (p3->next->no==aid)
	p2=p3->next;
	if (!p3)
	   {
	   	printf("����������������룡\n");
	   } 
	cout<<"���Ϊ��"<<aid<<"�Ĳ˼�Ϊ��"<<p2->money;
	
	int c;
	cout<<"��������ĵļ۸�"<<endl;
	cin>>c;
	p2->money=c;
	 
	FILE *fp1;
    fp1=fopen("menu.txt","w");
    menu * q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%d %s %d %d \n",q1->no,q1->mname,q1->money,q1->mark);
		q1=q1->next ;
	}
    fclose(fp1);
    
    cout<<"������� 1  �����ϼ��˵� 0"<<endl;
	    int d;
    	cin>>d;
    	cout<<endl;
    	if(d==1)
	      {goto PRND;} 
    
	
	
	}    	
   	
   	
}	
	
	
    	
	
	

