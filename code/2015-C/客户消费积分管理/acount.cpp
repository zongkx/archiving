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
	
    /*临时存储文件内数据的数组*/ 
    char newname[100][100];//姓名 
	char newctime[200][200];//创建时间 
	char newdtime[200][200];//销户时间 
	char newpassword[200][200];//密码
	long newac[200];//账号 
	long newid[200];//身份证号码 
	int newmoney[200];//余额 
	int newmark[200]={0};//挂失标记 
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
    printf("----------请输入账号-----------\n");
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
	   	printf("输入错误\n");goto PRN;
	   }
	printf("姓名    身份证号    余额\n");
	printf("%s  %ld  %d\n\n",p2->name,p2->id,p2->money );
	int f;
	f=p2->money; //记录当时余额 
	int index;
	printf("----存款请按1  取款请按2------\n");
	scanf("%d",&index);
ASD:	printf("----------请输入发生额(为正)-----------\n");
	int a;//记录发生额 
	scanf("%d",&a);
	if(a>p2->money&&index==2 )
	{
	   printf("您的余额不够，请重新输入发生额\n");
	   goto ASD;	
	}
	int e;//记录存取款后的余额 
	if(index==1)
	{   
	    p2->money=p2->money+a;
	    
	    printf("存款成功\n");
		
	} 
	if(index==2)
	{
		p2->money=p2->money-a;
		
		printf("取款成功\n");
		
	}
	e=p2->money;
	int d;
	printf("需要保存记录请按 1，否则请按 0\n");
	scanf("%d",&d); 
	if(d=1)
		{record(c,a,index,f);}
	printf("继续存款请按 1，返回请按 2\n");
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
	

