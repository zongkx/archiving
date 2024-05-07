#include"sum.h"	


//读取文件函数
void read(linklist &head)
{
	FILE *fp;
	linklist rear,q;
    head=new link;
	head->next=NULL;
	rear=head;
	if((fp=fopen("costomer.txt","a+"))==NULL)
	{
       	printf("打开文件失败\n");
       	exit(0);
	}
    cosnode p[100];
	for(int i=0;i<10000;i++)
	{  
	   strcpy(p[i].name,"#");
	   fscanf(fp,"%s%s%lf%lf%lf",p[i].name,p[i].ID,&p[i].consume,&p[i].s,&p[i].y);
	   if(strcmp(p[i].name,"#")==0)
	     break;
	   q=new link;
	   strcpy(q->a->name,p[i].name);
	   strcpy(q->a->ID,	p[i].ID);
	   q->a->consume=p[i].consume;
	   q->a->s=p[i].s;
	   q->a->y=p[i].y;
	   rear->next=q;
	   rear=q;
	}
	rear->next=NULL; 
	fclose(fp);
	linklist k=head->next;
	while(k){
	  printf("%s %s %.2f %.1f %.1f\n",k->a->name,k->a->ID,k->a->consume,k->a->s,k->a->y);
	  k=k->next;
}
}
