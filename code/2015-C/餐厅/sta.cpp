#include"res.h"

void sta()
{
	
PRND:	system("cls");
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
	
	
	cout<<"������˵ı��:"<<endl;
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
	   	printf("����������������룡\n");
	   } 
	cout<<"���Ϊ��"<<aid<<"�ĵ�ʹ���Ϊ��"<<p2->mark<<endl;
	
	cout<<"�����鿴 1    �����ϼ��˵� 0"<<endl;
	    int d;
    	cin>>d;
    	cout<<endl;
    	if(d==1)
	      {goto PRND;} 
    
	
	
	}    	
	

