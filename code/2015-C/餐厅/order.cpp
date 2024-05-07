#include"res.h"


void order()
{
	system("cls");
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
	printf("\t\t编号       名字       价格        点菜标记\n");

    menu *k;
	k=head->next  ;
	
	while(k)
	{
		printf("\t       %d            %s          %d           %d\n",k->no,k->mname,k->money,k->mark);
		k=k->next ;
	}
	
	
	
	
	int m=0;
while(1){
	cout<<"请输入菜的编号"<<endl;
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
	   	printf("输入错误，请重新输入！\n");
	   } 
	
	p2->mark++;
	
	m=m+p2->money;

	cout<<"                     您点的菜名为："<<p2->mname<<endl;
	cout<<"                     您点的菜的价格为："<<p2->money<<endl;
	int x;
	cout<<"\t\t继续点餐请按1  返回请按 2"<<endl;
	cin>>x;
	if(x==2)
	  {
	  break;}
}
/*mark改变后写入文件*/ 
    FILE *fp2;
    fp2=fopen("menu.txt","w");
    menu * q1=head->next;
    while(q1)
	{
		fprintf(fp2,"%d %s %d %d \n",q1->no,q1->mname,q1->money,q1->mark);
		q1=q1->next ;
	}
    fclose(fp2);
	
    
    cout<<"\t\t您所点的饭价一共是："; 
	printf("%d\n",m);
	system("pause");
	
	

	
	/*acount功能的数据写入文件*/ 

    /*创建时间生成函数*/ 
	time_t ctime;
    struct tm *timeinfo;
    time(&ctime);
    timeinfo=localtime(&ctime);
    int a,b,c;
    int yearr[10],monn[3],dayy[3];
    a=timeinfo->tm_year+1900;
    b=timeinfo->tm_mon+1;
    c=timeinfo->tm_mday;
    FILE *fp1=fopen("acount.txt","a+");
    fprintf(fp,"%d %d %d %d\n",a,b,c,m);
    fclose(fp1);
	


 } 
