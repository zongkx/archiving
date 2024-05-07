
#include"bank.h"

void record(long c,int a,int index,int f)//传递账号，发生额 ，存取标记，之前余额 
{
    node *q;
	q=new node;
	q->next=NULL;
	
	/*创建时间生成函数*/ 
	time_t ctime;
    struct tm *timeinfo;
    time(&ctime);
    timeinfo=localtime(&ctime);
    int h,b,g;
    int yearr[10],monn[3],dayy[3];
    q->year =timeinfo->tm_year+1900;
    q->month =timeinfo->tm_mon+1;
    q->day =timeinfo->tm_mday;

    if(index==1)
        {
    	q->cmark ='+';
		q->zyue =f+a;
  	    }
	else
	    {
		q->cmark ='-';
		q->zyue=f-a;
		} 
	q->dac=c;
	q->bmoney=a;
	q->yue=f;
	
	 
	FILE *fp;
	fp=fopen("bankrecord.txt","a+");
    fprintf(fp,"%ld %d %02d %02d %d %c %d %d\n",q->dac,q->year,q->month,q->day,q->yue,q->cmark,q->bmoney,q->zyue) ;   
	fclose(fp);
	
}
