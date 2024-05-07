#include"supermarket.h"


int  check(int a)
{
    linklist head;
    move(head);
    linklist p1=head;
    while(p1->next&&p1->next->a->id!=a)
       {p1=p1->next;}
    if(p1->next==NULL)
       {return 0;}
    else
	    return 1; 
 
}
