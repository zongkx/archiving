#include"supermarket.h"

void move(linklist &head)
{
	FILE *fp;
	point p[100];
	linklist rear;
	linklist q;
	head=(linklist)malloc(sizeof(link));
	head->next=NULL;
	rear=head;
	int i;
	fp=fopen("supermarket.txt","r+");
    for(i=1;i<100;i++)
	{ 
	    
		p[i].id=0;
		fscanf(fp,"%d%s%lf%lf%d%s",&p[i].id,p[i].name,&p[i].iprice,&p[i].oprice,&p[i].number,p[i].itime );
		if(p[i].id==0)
		   { break; }
	    q=(linklist)malloc(sizeof(point));
	    q->a->oprice=p[i].oprice;
		q->a->iprice=p[i].iprice;
		q->a->number=p[i].number;
		q->a->id =p[i].id;
		strcpy(q->a->name,p[i].name); 
		strcpy(q->a->itime,p[i].itime);
		rear->next=q;
		rear=q;
	}
	rear->next=NULL; 
	fclose(fp);

}
