
#include"bank.h"

void selfhelp()
{   
  while(1){
  	system("cls");
	linklist head,rear;
	linklist q;
	head=(linklist)malloc(sizeof(point));
	head->next=NULL;
	rear=head;
	int i,c,d;
    /*��ʱ�洢�ļ������ݵ�����*/ 
    char newname[100][100];//���� 
	char newctime[200][200];//����ʱ�� 
	char newdtime[200][200];//����ʱ�� 
	char newpassword[200][200];//����
	long newac[200];//�˺� 
	long newid[200];//���֤���� 
	int newmoney[200];//��� 
	int newmark[200]={0};//��� 
	int newmark1[200];
	FILE *fp;
	fp=fopen("saveuser.txt","r+");
	
	for(i=1;i<100;i++)
	{
		fscanf(fp,"%s%s%s%s%ld%ld%d%d%d",newname[i],newctime[i],newdtime[i],newpassword[i],&newac[i],&newid[i],&newmoney[i],&newmark[i],&newmark1[i]);
		if(newmark[i]==0)
		   { break; }
	    q=(linklist)malloc(sizeof(point));
		q->money=newmoney[i];
		q->id =newid[i];
		q->mark =newmark[i];
		q->mark1 =newmark1[i];
		q->ac =newac[i];
		strcpy(q->name ,newname[i]); 
		strcpy(q->ctime,newctime[i]);
		strcpy(q->dtime,newdtime[i]);
		strcpy(q->password,newpassword[i]);
		rear->next=q;
		rear=q;
	}
	rear->next=NULL; 
	fclose(fp);
	
	
	
PRN:	printf("---------------��ӭʹ���������ܣ�����������˺�---------------\n");
	long a; 
	scanf("%ld",&a);
	linklist p2;
    linklist p1=head;
    
	
	int v;
	v=check(a);
	if(v==0){
		printf("\n\n\t\t�����˺Ŵ��ڹ�ʧ״̬���뵽ǰ̨������ز�����\n");
		int z;
		printf("�� 0 �˳���");
		scanf("%d",&z);
		if(z==0) 
		  break;
         	}
    int u;
    u=check1(a);
    if(u==0){
		printf("\n\n\t\t�����˺Ų����ڣ��뵽ǰ̨������ز�����\n");
		int z;
		printf("�� 0 �˳���");
		scanf("%d",&z);
		if(z==0) 
		  break;
         	}
   
   
    while(p1&&p1->next->ac!=a)
       {
       	 p1=p1->next;
	   }
	if (p1->next->ac==a)
	    p2=p1->next;
	if (!p1)
	   {
	   	printf("����������������룡\n");goto PRN;
	   }
       
	printf("---------------����������-----------------��\n");
	char n[5];
	int x=0;
    while(1){
               n[x]=getch();
               if(n [x]!='\r')
			       {printf("*"); ++x;}
               else break;
                    }
    n [x]='\0';
    printf("\n");
    if(strcmp(n,p2->password )!=0)
	{
		printf("�����������������룡\n") ;
	}
    int y=p2->money ;




PRNS:    system("cls");
	play1();
  	int r;
  	scanf("%d",&r);
  	if(r==1)
  	  change();//���������֤�ţ� 
  	if(r==2)
	  find(a,n,y);// 
    if(r==3)
	  look(a);
	printf("\n\n�����������������밴 1�������ܽ����밴 0 \n");
	int b;
	scanf("%d",&b);
	if(b==1)
	{
		goto PRNS;
	}
}
} 
