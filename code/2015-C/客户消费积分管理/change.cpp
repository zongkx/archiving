
#include"bank.h"


void change()
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
	
	

PRN:	printf("---------------欢迎使用改密功能，请输入你的身份证号---------------\n");
	long a; 
	scanf("%ld",&a);
	linklist p2;
    linklist p1=head;
    while(p1&&p1->next->id!=a)
       {
       	 p1=p1->next;
	   }
	if (p1->next->id ==a)
	    p2=p1->next;
	if (!p1)
	   {
	   	printf("输入错误，请重新输入！\n");goto PRN;
	   }
       
    char s[6],t[6];
PRN2:	printf("请输入你的新密码！\n");
    int x=0;int j=0;
    while(1){
               s[x]=getch();
               if(s [x]!='\r')
			       {printf("*"); ++x;}
               else break;
                    }
    s [x]='\0';
    printf("\n");
    
	printf("请再次输入你的新密码！\n");
     
    while(1){
               t[j]=getch();
               if(t[j]!='\r')
			       {printf("*"); ++j;}
               else break;
                    }
    t [j]='\0';
    printf("\n");
    int index;
    
	if(strcmp(s,t)==0)
	   index=1;     
	if(index==1)
	{
	   strcpy(p2->password ,s );
	   printf ("修改成功！\n");
	}
	else
    {
    	printf("您输入的两次密码不同，请检查后再次输入！\n");goto PRN2;
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
	
	

