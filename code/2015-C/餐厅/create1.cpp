#include"res.h"


void create1()
{  

  system("cls"); 
  printf("\n\n\n\n\n\t\t\t\t");
  printf("****����  1****\n\t\t\t\t****����  2****\n\t\t\t\t****����  3****\n"); 
    
  int a;
  scanf("%d",&a);
if(a==1)//�������û� 
  {
    
PRN: system("cls");
    man *q;
    q=new man;
    FILE *fp=fopen("manager.txt","a");
	
	printf("��������������:\n");
    cin>>q->name;
    cout<<"���������Ĺ���Ա���:"<<endl;
	cin>>q->id;  
	
	cout<<"�������������룬��ʼ����Ϊ 000000 ���޸��밴 1 �����밴 0 "<<endl;
	strcpy(q->pswd,"000000" );
		int x=0, j;cin>>j;
	  while(j>0)
	   {
	        while(1)
			{
               q->pswd[x]=getch();
               if(q->pswd [x]!='\r')
			       {printf("*"); ++x;}
               else break;
            }
            q->pswd [x]='\0';
            printf("\n");break;
		}         //�������� 
    q->next =NULL; 
    fprintf(fp,"%s %d %s\n",q->name ,q->id ,q->pswd );
    fclose(fp);
    cout<<"�����ɹ�����ӭ�´�ʹ��!"<<endl<<endl<<endl;
	cout<<"\t\t\t****�������� 1  �����ϼ��˵� 0****"<<endl;
	int d;
	cin>>d;
	cout<<endl;
	if(d==1)
	  {goto PRN;} 
	}
if(a==2)//�����û� 
   {
PRNA:  system("cls");
   	int i;
	man *head,*rear;
	man *q;
	
	head=new man;
	head->next=NULL;
	rear=head;
   	FILE *fp;
	fp=fopen("manager.txt","r+");
	//������ʱ����洢 
	char nname[20][20];
	int nid[20]={0};
	char npswd[20][20];
	
   	for(i=1;i<100;i++)
   	{   
   		fscanf(fp,"%s%d%s",nname[i],&nid[i],npswd[i] );
   		if(nid[i]==0 )
		   {break;}
		q=(lman)malloc(sizeof(man));   
		q->id =nid[i];
		strcpy(q->name ,nname[i]);
		strcpy(q->pswd ,npswd[i]);
		
		rear->next=q;
		rear=q;   
	}
	rear->next=NULL; 
	fclose(fp);
	
	
	cout<<"����������id:"<<endl;
	int aid;
	cin>>aid;
	
	
	man * p2;
    man * p3=head;
while(p3!=NULL&&p3->next->id!=aid)
       {
       	 p3=p3->next;
	   }
	if (p3->next->id==aid)
	p2=p3->next;
	if (!p3)
	   {
	   	printf("����������������룡\n");
	   
	   } 
	
	printf("%s %s\n",p2->name,p2->pswd);
	
	FILE *fp1;
    fp1=fopen("manager.txt","w");
    man * q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%s %d %s \n",q1->name,q1->id,q1->pswd);
		q1=q1->next ;
	}
    fclose(fp1);
	
	
	cout<<"�������� 1  �����ϼ��˵� 0"<<endl;
	int d;
	cin>>d;
	cout<<endl;
	if(d==1)
	  {goto PRNA;} 
	}
if(a==3)//�������� 
{
	system("cls");
   	int i;
	man *head,*rear;
	man *q;
	
	head=new man;
	head->next=NULL;
	rear=head;
   	FILE *fp;
	fp=fopen("manager.txt","r+");
	
	char nname[20][20];
	int nid[20]={0};
	char npswd[20][20];
	
	
	
	
   	for(i=1;i<100;i++)
   	{   
   		fscanf(fp,"%s%d%s",nname[i],&nid[i],npswd[i] );
   		if(nid[i]==0 )
		   {break;}
		q=(lman)malloc(sizeof(man));   
		q->id =nid[i];
		strcpy(q->name ,nname[i]);
		strcpy(q->pswd ,npswd[i]);
		
		rear->next=q;
		rear=q;   
	}
	rear->next=NULL; 
	fclose(fp);
	
	
	cout<<"�����������˺�:"<<endl;
	int a;
	scanf("%d",&a);
	
	
	man * p2;
    man * p3=head;
while(p3!=NULL&&p3->next->id!=a)
       {
       	 p3=p3->next;
	   }
	if (p3->next->id==a)
	p2=p3->next;
	if (!p3)
	   {
	   	printf("����������������룡\n");
	   } 
	   
PRN2:	printf("��������������룡\n");
    char s[5],t[5];
    scanf("%s",s);
	printf("���ٴ�������������룡\n");
    scanf("%s",t);
	if(strcmp(s,t)!=0)
	  {
	  cout<<"����������������벻ͬ������������!"<<endl;
	  goto PRN2;}
	strcpy(p2->pswd,t);
	cout<<"���ܳɹ���"<<endl;
	
	
	FILE *fp1;
    fp1=fopen("manager.txt","w");
    man * q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%s %d %s \n",q1->name,q1->id,q1->pswd);
		q1=q1->next ;
	}
    fclose(fp1);
	
	

	 	
}

	
}

	

	

