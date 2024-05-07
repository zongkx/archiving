#include"sum.h"	


void load(linklist head)
{
	FILE *fp;
	linklist p;
    head->next=p;
	p=new link;
	fp=fopen("costomer.txt","a+");
	
	while(fscanf(fp,"%s %s %.2f %.0f %.1f\n",p->a->name ,p->a->ID ,&p->a->consume ,&p->a->s,&p->a->y)!=EOF)
	
	{
		p=p->next;
		
	}
     	p->next=NULL;
		fclose(fp);
	
	
	
	
	
	
	
}
