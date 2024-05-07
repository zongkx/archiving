#include"bank.h"

void deleteuser()
{
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
	
	linklist k;
	k=head->next;
	printf("下面是所有的用户信息（不含密码）\n每行的最后一位是用户的账号，您可以通过账号来管理\n");
	printf("姓名         创建时间        身份证号           余额          账号\n");
	while(k)
	{
		printf("%s  %s  %ld  %ld  %d \n",k->name,k->ctime,k->id,k->money,k->ac );
		k=k->next;
}
   while(1)
{
   
    printf("请输入想要删除的账号：");
    int deac;
    scanf("%d",&deac);
    linklist p2;
    linklist p1=head;
    while(p1&&p1->next->ac!=deac)
       {
       	 p1=p1->next;
	   }
	if (p1->next->ac==deac)
	p2=p1->next;
	if (!p1)
	   {
	   	printf("输入错误，请重新输入！\n");break;
	   
	   }
       
    printf("确定删除么？1 是 2 否\n");
	int chioce;
	scanf("%d",&chioce);
	if (chioce==1)
	{
		p2->mark1  =-1;
		
		/*创建时间生成函数*/ 
	  time_t ctime;
      struct tm *timeinfo;
      time(&ctime);
      timeinfo=localtime(&ctime);
      int a,b,c;
      char yearr[10],monn[3],dayy[3];
      a=timeinfo->tm_year+1900;
      b=timeinfo->tm_mon+1;
      c=timeinfo->tm_mday;
      itoa(a, yearr, 10);
      itoa(b, monn, 10);
      itoa(c, dayy, 10);
      strcat(yearr,"-");
      strcat(yearr,monn);
      strcat(yearr,"-");
	  strcat(yearr,dayy);
      strcpy(p2->dtime ,yearr);
        printf("销户成功！\n");break;
	}
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
