#include"bank.h"


void loss()
{
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
	long newac[200]={0};//�˺� 
	long newid[200];//���֤���� 
	int newmoney[200];//��� 
	int newmark[200];//��� 
	int  newmark1[200];
	
	FILE *fp;
	fp=fopen("saveuser.txt","r+");
	
	for(i=1;i<100;i++)
	{
		fscanf(fp,"%s%s%s%s%ld%ld%d%d%d",newname[i],newctime[i],newdtime[i],newpassword[i],&newac[i],&newid[i],&newmoney[i],&newmark[i],&newmark1[i]);
		if(newac[i]==0)
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

	
  


	long l;
	printf("\t\t\t��������Ҫ�������˺ţ�\n");
    scanf("%ld",&l);
	linklist p2;
    linklist p1=head;
   
    
    while(p1&&p1->next->ac!=l)
       {
       	 p1=p1->next;
	   }
	if (p1->next->ac==l)
	    p2=p1->next;
    if(!p1)
	   {
	   	printf("�������\n");
	   }
	printf("��ʧ 1 ��� 2\n");
	
	int s;
	scanf("%d",&s);
	if(s==1)
	  {
	   p2->mark= -1;
	   printf("��ʧ�ɹ���\n"); 
	  }
	   
	else
	  {
	  p2->mark = 1;
	  printf("����ɹ���\n");
	  } 
	    
	printf("\t\t\t�����ɹ���\n ���������˺��ǣ�\n");
    printf("%ld\n",p2->ac );
   
    FILE *fp1;
    fp1=fopen("saveuser.txt","w");
    linklist q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%s %s %s %s %ld %ld %d %d %d\n",q1->name,q1->ctime,q1->dtime,q1->password,q1->ac,q1->id,q1->money,q1->mark,q1->mark1);
		q1=q1->next ;
	}
    fclose(fp1);
	
}

	


   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
