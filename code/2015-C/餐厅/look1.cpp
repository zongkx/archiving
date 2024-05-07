#include"res.h"

void  look1()
{
	system("cls");
	cout<<" "<<endl; 
	int i;
	user *head,*rear;
	user *q;
	
	head=(user *)malloc(sizeof(user));
	head->next=NULL;
	rear=head;
    FILE *fp;
	fp=fopen("user.txt","r+");
	
	char nname[50][50];
	long nid[50]={0};
	char nsex[50];
	char  ntime[50][50];
	
	for(i=1;i<100;i++)
   	{   
   	    fscanf(fp,"%s%d %c%s",nname[i],&nid[i],&nsex[i],ntime[i]);
   	     if(nid[i]==0)
		    break;
		q=(user *)malloc(sizeof(user));   
		strcpy(q->uname ,nname[i]);
		q->uid=nid[i];
		q->sex =nsex[i];
		strcpy(q->time ,ntime[i]);
		rear->next=q;
		rear=q;   
	}
	rear->next=NULL; 
	fclose(fp);
	printf("姓名       id       性别（男：m，女：f）        创建日期\n");

    user *k;
	k=head->next;
	
	

	while(k)
	{
		printf("%s       %ld      %c        %s\n",k->uname,k->uid,k->sex,k->time);
		k=k->next ;
	}
	
	
	cout<<" 返回上级菜单 0"<<endl;int d;cin>>d;
	
	
 } 
