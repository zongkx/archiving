#include"bank.h"


void acount()
{   
    int i;
    system("cls");
	linklist head,rear;
	linklist q;
	head=(linklist)malloc(sizeof(point));
	head->next=NULL;
	rear=head;
	
    /*��ʱ�洢�ļ������ݵ�����*/ 
    char newname[100][100];//���� 
	char newctime[200][200];//����ʱ�� 
	char newdtime[200][200];//����ʱ�� 
	char newpassword[200][200];//����
	long newac[200];//�˺� 
	long newid[200];//���֤���� 
	int newmoney[200];//��� 
	int newmark[200]={0};//��ʧ��� 
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
	
	

while(1)
{
    linklist p2;
    linklist p1=head;
    printf("----------�������˺�-----------\n");
    long c;
PRN:    scanf("%ld",&c);
    
    while(p1&&p1->next->ac!=c)
       {
       	 p1=p1->next;
	   }
	if (p1->next->ac==c)
	    p2=p1->next;
	if(!p1)
	   {
	   	printf("�������\n");goto PRN;
	   }
	printf("����    ���֤��    ���\n");
	printf("%s  %ld  %d\n\n",p2->name,p2->id,p2->money );
	int f;
	f=p2->money; //��¼��ʱ��� 
	int index;
	printf("----����밴1  ȡ���밴2------\n");
	scanf("%d",&index);
ASD:	printf("----------�����뷢����(Ϊ��)-----------\n");
	int a;//��¼������ 
	scanf("%d",&a);
	if(a>p2->money&&index==2 )
	{
	   printf("�������������������뷢����\n");
	   goto ASD;	
	}
	int e;//��¼��ȡ������� 
	if(index==1)
	{   
	    p2->money=p2->money+a;
	    
	    printf("���ɹ�\n");
		
	} 
	if(index==2)
	{
		p2->money=p2->money-a;
		
		printf("ȡ��ɹ�\n");
		
	}
	e=p2->money;
	int d;
	printf("��Ҫ�����¼�밴 1�������밴 0\n");
	scanf("%d",&d); 
	if(d=1)
		{record(c,a,index,f);}
	printf("��������밴 1�������밴 2\n");
	int chioce;
	scanf("%d",&chioce);
	if(chioce==2){break;}
}
 
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
	

