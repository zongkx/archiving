#include"supermarket.h"

void deleteuser()
{
	system("cls");
	int i;
    linklist head;
	move(head);
    linklist k;
	k=head->next;
	printf("下面是所有信息\n");
	while(k)
	{
		printf("%d %s %.2f %.2f %d %s\n",k->a->id ,k->a->name,k->a->iprice,k->a->oprice,k->a->number,k->a->itime );
		k=k->next;
	}
PRN:
	printf("请输入想要删除的id：");
    int did;
    scanf("%d",&did);
    linklist p2;
    linklist p1=head;
    while(p1->next&&p1->next->a->id!=did)
       {
       	 p1=p1->next;
	   }
	if(p1->next==NULL)
	{ 
	   cout<<"error";
	   goto PRN;
	}
    else
	{
		
	p2=p1->next;   
    p1->next=p2->next;
    delete(p2);
    cout<<"ok!";
	FILE *fp1;
    fp1=fopen("supermarket.txt","w");
    linklist q1=head->next;
    while(q1)
	{
		fprintf(fp1,"%d %s %.2f %.2f %d %s\n",q1->a->id ,q1->a->name,q1->a->iprice,q1->a->oprice,q1->a->number,q1->a->itime);
		q1=q1->next ;
	}
    fclose(fp1);
   }
} 



