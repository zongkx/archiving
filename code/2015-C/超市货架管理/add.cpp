#include"supermarket.h"

void add()
{
	system("cls");
	int i;
    linklist head;
	move(head);

PRN:
	printf("存货 1   取货 2  \n"); 
	int a;
	cin>>a;
	printf("请输入想要存取货的id,以及数目：\n");
	
    int did,n;
    scanf("%d %d",&did,&n);
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
	if(a==1) 
 	   p2->a->number=p2->a->number+n;
 	
	else
	   {
	    if(n<p2->a->number)
	      {
	      p2->a->number=p2->a->number-n;
	      record(did,p2->a->number,n);} 
	    else
		  {
		  cout<<"error!!!!!";
		  goto PRN;}	
	   }
	cout<<"成功！该货物的目前余量为："<<p2->a->number<<endl;       
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
