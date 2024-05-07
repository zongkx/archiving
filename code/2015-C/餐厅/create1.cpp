#include"res.h"


void create1()
{  

  system("cls"); 
  printf("\n\n\n\n\n\t\t\t\t");
  printf("****开户  1****\n\t\t\t\t****查找  2****\n\t\t\t\t****改密  3****\n"); 
    
  int a;
  scanf("%d",&a);
if(a==1)//创建新用户 
  {
    
PRN: system("cls");
    man *q;
    q=new man;
    FILE *fp=fopen("manager.txt","a");
	
	printf("请输入您的姓名:\n");
    cin>>q->name;
    cout<<"请输入您的管理员编号:"<<endl;
	cin>>q->id;  
	
	cout<<"请输入您的密码，初始密码为 000000 ，修改请按 1 否则请按 0 "<<endl;
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
		}         //隐藏密码 
    q->next =NULL; 
    fprintf(fp,"%s %d %s\n",q->name ,q->id ,q->pswd );
    fclose(fp);
    cout<<"创建成功，欢迎下次使用!"<<endl<<endl<<endl;
	cout<<"\t\t\t****继续开户 1  返回上级菜单 0****"<<endl;
	int d;
	cin>>d;
	cout<<endl;
	if(d==1)
	  {goto PRN;} 
	}
if(a==2)//查找用户 
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
	//定义临时数组存储 
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
	
	
	cout<<"请输入您的id:"<<endl;
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
	   	printf("输入错误，请重新输入！\n");
	   
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
	
	
	cout<<"继续查找 1  返回上级菜单 0"<<endl;
	int d;
	cin>>d;
	cout<<endl;
	if(d==1)
	  {goto PRNA;} 
	}
if(a==3)//更改密码 
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
	
	
	cout<<"请输入您的账号:"<<endl;
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
	   	printf("输入错误，请重新输入！\n");
	   } 
	   
PRN2:	printf("请输入你的新密码！\n");
    char s[5],t[5];
    scanf("%s",s);
	printf("请再次输入你的新密码！\n");
    scanf("%s",t);
	if(strcmp(s,t)!=0)
	  {
	  cout<<"您的输入的两次密码不同，请重新输入!"<<endl;
	  goto PRN2;}
	strcpy(p2->pswd,t);
	cout<<"改密成功！"<<endl;
	
	
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

	

	

