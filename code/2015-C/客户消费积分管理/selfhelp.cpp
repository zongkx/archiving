
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
    /*临时存储文件内数据的数组*/ 
    char newname[100][100];//姓名 
	char newctime[200][200];//创建时间 
	char newdtime[200][200];//销户时间 
	char newpassword[200][200];//密码
	long newac[200];//账号 
	long newid[200];//身份证号码 
	int newmoney[200];//余额 
	int newmark[200]={0};//标记 
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
	
	
	
PRN:	printf("---------------欢迎使用自助功能，请输入你的账号---------------\n");
	long a; 
	scanf("%ld",&a);
	linklist p2;
    linklist p1=head;
    
	
	int v;
	v=check(a);
	if(v==0){
		printf("\n\n\t\t您的账号处于挂失状态！请到前台进行相关操作！\n");
		int z;
		printf("按 0 退出！");
		scanf("%d",&z);
		if(z==0) 
		  break;
         	}
    int u;
    u=check1(a);
    if(u==0){
		printf("\n\n\t\t您的账号不存在！请到前台进行相关操作！\n");
		int z;
		printf("按 0 退出！");
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
	   	printf("输入错误，请重新输入！\n");goto PRN;
	   }
       
	printf("---------------请输入密码-----------------：\n");
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
		printf("密码有误，请重新输入！\n") ;
	}
    int y=p2->money ;




PRNS:    system("cls");
	play1();
  	int r;
  	scanf("%d",&r);
  	if(r==1)
  	  change();//需输入身份证号！ 
  	if(r==2)
	  find(a,n,y);// 
    if(r==3)
	  look(a);
	printf("\n\n进行其他自助服务请按 1，返回总界面请按 0 \n");
	int b;
	scanf("%d",&b);
	if(b==1)
	{
		goto PRNS;
	}
}
} 
